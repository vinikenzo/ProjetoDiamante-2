package br.com.restaurante.userservice.service;

import br.com.restaurante.userservice.dtos.request.NewUserRequest;
import br.com.restaurante.userservice.dtos.response.NewUserResponse;
import br.com.restaurante.userservice.entity.User;
import br.com.restaurante.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public NewUserResponse newUser(NewUserRequest dto){
        User user = new User();
        user.setNome(dto.nome());

        userRepository.save(user);
        log.info("Usuário cadastrado com sucesso. Nome do usuário: ${} Id do usuário: ${}", user.getNome(), user.getId());
        return new NewUserResponse(user.getId(), user.getNome());
    }

    //métodos crud basicos de user
    //método para cadastrar tipo favorito de comida
    //metodo para cadastar localizacao
    //metodo para cadastrar preco disposto a pagar
    //metodos que virão do microserviço recommendationservice futuramente
}
