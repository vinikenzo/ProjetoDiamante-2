package br.com.restaurante.recomendationservice.controller;

import br.com.restaurante.recomendationservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getRecommendation(@PathVariable Long userId){
        String aiResponse = recommendationService.generateRecommendation(userId);
        return ResponseEntity.ok(aiResponse);
    }
}
