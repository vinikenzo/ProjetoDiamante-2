package br.com.restaurante.restauranteservice.service;

import br.com.restaurante.restauranteservice.entity.Restaurante;
import br.com.restaurante.restauranteservice.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestauranteService {
    private final RestauranteRepository restauranteRepository;

    public List<Restaurante> getResturantRecommendations(String location, String category, double rating){
        return restauranteRepository.findByCategoryAndLocationAndRatingGreaterThanEqual(category, location, rating);
    }
}
