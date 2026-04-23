package br.com.restaurante.recomendationservice.service;

import br.com.restaurante.recomendationservice.dto.AIResponseDto;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {
    private final RestClient restClient;
    private final ChatClient chatClient;

    public RecommendationService(@LoadBalanced RestClient.Builder restClientBuilder, ChatClient.Builder chatClientBuilder) {
        this.restClient = restClientBuilder.build();
        this.chatClient = chatClientBuilder.build();
    }

    record UserData(String tipoComidaFavorita, String localizacao, BigDecimal precoDispostoAPagar) {}
    record RestaurantData(String nome, Double averagePrice, Double rating) {}
    record SearchRequestDto(String location, String category, Double minRating) {}

    public AIResponseDto generateRecommendation(Long userId) {
        UserData user = restClient.get()
                .uri("http://userservice/users/" + userId)
                .retrieve()
                .body(UserData.class);

        SearchRequestDto searchRequestDto = new SearchRequestDto(
                user.localizacao(),
                user.tipoComidaFavorita(),
                3.0
        );

        List<RestaurantData> restaurants = restClient.post()
                .uri("http://restauranteservice/restaurants/search")
                .body(searchRequestDto)
                .retrieve().body(new ParameterizedTypeReference<List<RestaurantData>>() {
                });

        String userPreferencesText = String.format("Food: %s, Location: %s, Budget: R$%.2f",
                user.tipoComidaFavorita(), user.localizacao(), user.precoDispostoAPagar());

        String availableRestaurantsText = restaurants.stream()
                .map(r -> String.format("- %s (Rating: %.1f, Avg Price: R$%.2f)", r.nome(), r.rating(), r.averagePrice()))
                .collect(Collectors.joining("\n"));

        String promptText = String.format("""
            Você é um renomado crítico gastronômico e um especialista em recomendar restaurantes.
            
            PREFERÊNCIAS DO USUÁRIO:
            %s
            
            RESTAURANTES DISPONÍVEIS:
            %s
            
            INSTRUÇÕES:
            Analise os restaurantes disponíveis e escolha a MELHOR opção que atenda perfeitamente às preferências do usuário. 
            Considere o orçamento cuidadosamente. 
            Explique o motivo da sua escolha em um parágrafo curto e amigável. Não liste os outros restaurantes.
            Responda exclusivamente em português do Brasil.
            """, userPreferencesText, availableRestaurantsText);

        return new AIResponseDto(chatClient.prompt()
                .user(promptText)
                .call()
                .content());
    }
}
