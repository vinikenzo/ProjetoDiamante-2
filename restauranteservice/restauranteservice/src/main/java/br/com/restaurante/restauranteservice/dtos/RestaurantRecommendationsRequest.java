package br.com.restaurante.restauranteservice.dtos;

public record RestaurantRecommendationsRequest(String category, String location, Double minRating) {
}
