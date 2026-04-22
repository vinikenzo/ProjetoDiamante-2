package br.com.restaurante.restauranteservice.repository;

import br.com.restaurante.restauranteservice.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    List<Restaurante> findByCategoryAndLocationAndRatingGreaterThanEqual(String category, String location, Double minRating);
}
