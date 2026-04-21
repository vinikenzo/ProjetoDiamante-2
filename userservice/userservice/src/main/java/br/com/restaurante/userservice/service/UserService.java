package br.com.restaurante.userservice.service;

import br.com.restaurante.userservice.dtos.response.NewUserResponse;
import br.com.restaurante.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //public NewUserResponse newUser(NewUserRequest dto){

    //}

    //métodos crud basicos de user
    //método para cadastrar tipo favorito de comida
    //metodo para cadastar localizacao
    //metodo para cadastrar preco disposto a pagar
    //metodos que virão do microserviço recommendationservice futuramente
}
