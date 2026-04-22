package br.com.restaurante.userservice.controller;

import br.com.restaurante.userservice.dtos.request.FavoriteFoodRequest;
import br.com.restaurante.userservice.dtos.request.NewUserRequest;
import br.com.restaurante.userservice.dtos.request.PriceLimitRequest;
import br.com.restaurante.userservice.entity.User;
import br.com.restaurante.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> newUser(@RequestBody NewUserRequest dto){
        return new ResponseEntity<>(userService.newUser(dto), HttpStatus.CREATED);
    }

    @PostMapping("/{id_usuario}/favoriteFood")
    public ResponseEntity<?> registerFavoriteFood(@RequestBody FavoriteFoodRequest dto, @PathVariable Long id_usuario){
        return new ResponseEntity<>(userService.registerFavoriteFood(dto, id_usuario), HttpStatus.OK);
    }

    @PostMapping("/{id_usuario}/priceLimit")
    public ResponseEntity<?> setPriceLimit(@RequestBody PriceLimitRequest dto, @PathVariable Long id_usuario){
        return new ResponseEntity<>(userService.PriceLimit(dto, id_usuario), HttpStatus.OK);
    }

    @GetMapping("/{id_usuario}")
    public ResponseEntity<?> getUserRecommendations(@PathVariable Long id_usuario){
        User usuario = userService.getUserById(id_usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

}
