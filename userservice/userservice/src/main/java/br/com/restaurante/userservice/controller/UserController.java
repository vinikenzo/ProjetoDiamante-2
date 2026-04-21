package br.com.restaurante.userservice.controller;

import br.com.restaurante.userservice.dtos.request.NewUserRequest;
import br.com.restaurante.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> newUser(@RequestBody NewUserRequest dto){
        return new ResponseEntity<>(userService.newUser(dto), HttpStatus.CREATED);
    }


}
