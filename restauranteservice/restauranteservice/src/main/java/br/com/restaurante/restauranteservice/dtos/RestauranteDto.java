package br.com.restaurante.restauranteservice.dtos;


import br.com.restaurante.restauranteservice.entity.Restaurante;

public record RestauranteDto(
        String nome,

        String category,

        String location,

        Double rating,

        Double averagePrice
) {
    public RestauranteDto(Restaurante restaurante) {
        this(restaurante.getNome(), restaurante.getCategory(), restaurante.getLocation(), restaurante.getRating(), restaurante.getAveragePrice());
    }
}
