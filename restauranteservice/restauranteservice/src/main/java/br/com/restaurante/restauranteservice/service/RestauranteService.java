package br.com.restaurante.restauranteservice.service;

import br.com.restaurante.restauranteservice.dtos.RestauranteDto;
import br.com.restaurante.restauranteservice.entity.Restaurante;
import br.com.restaurante.restauranteservice.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestauranteService {
    private final RestauranteRepository restauranteRepository;

    public List<Restaurante> getResturantRecommendations(String location, String category, double rating){
        return restauranteRepository.findByCategoryAndLocationAndRatingGreaterThanEqual(category, location, rating);
    }

    public List<RestauranteDto> listarRestaurantes(){
        List<Restaurante> lista = restauranteRepository.findAll();
        var restaurantes = lista.stream()
                .map(r -> new RestauranteDto(r.getNome(), r.getCategory(), r.getLocation(), r.getRating(), r.getAveragePrice()))
                .collect(Collectors.toList());
        return restaurantes;
    }
}
