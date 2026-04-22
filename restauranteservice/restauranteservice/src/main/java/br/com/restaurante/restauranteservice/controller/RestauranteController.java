package br.com.restaurante.restauranteservice.controller;

import br.com.restaurante.restauranteservice.dtos.RestaurantRecommendationsRequest;
import br.com.restaurante.restauranteservice.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestauranteController {
    private final RestauranteService restauranteService;

    @PostMapping("/search")
    public ResponseEntity<?> getRestaurantRecommendations(@RequestBody RestaurantRecommendationsRequest dto){
        return new ResponseEntity<>(restauranteService.getResturantRecommendations(dto.location(), dto.category(), dto.minRating()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> listarRestaurantes(){
        var restaurantes = restauranteService.listarRestaurantes();
        return ResponseEntity.ok(restaurantes);
    }
}
