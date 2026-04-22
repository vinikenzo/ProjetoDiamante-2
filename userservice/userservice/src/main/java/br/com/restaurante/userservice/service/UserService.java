package br.com.restaurante.userservice.service;

import br.com.restaurante.userservice.dtos.request.FavoriteFoodRequest;
import br.com.restaurante.userservice.dtos.request.NewUserRequest;
import br.com.restaurante.userservice.dtos.request.PriceLimitRequest;
import br.com.restaurante.userservice.dtos.response.NewUserResponse;
import br.com.restaurante.userservice.entity.User;
import br.com.restaurante.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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
        log.info("Usuário cadastrado com sucesso. Nome do usuário: {} Id do usuário: {}", user.getNome(), user.getId());
        return new NewUserResponse(user.getId(), user.getNome());
    }

    @Transactional
    public User registerFavoriteFood(FavoriteFoodRequest dto, Long idUsuario){
        Optional<User> userOptional = userRepository.getUserById(idUsuario);
        if (userOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        User usuario = userOptional.get();
        usuario.setTipoComidaFavorita(dto.comidaFavorita());
        userRepository.save(usuario);
        log.info("Comida favorita:{} registrada para Usuario {}", dto.comidaFavorita(), usuario.getId());

        return usuario;
    }

    @Transactional
    public User PriceLimit(PriceLimitRequest dto, Long idUsuario){
        Optional<User> userOptional = userRepository.getUserById(idUsuario);
        if (userOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        User usuario = userOptional.get();
        usuario.setPrecoDispostoAPagar(dto.precoLimite());
        userRepository.save(usuario);
        log.info("Preço limite setado ao usuario ${} para ${}", usuario.getId(), dto.precoLimite());

        return usuario;
    }

    public User getUserById(Long idUsuario){
        Optional<User> userOptional = userRepository.getUserById(idUsuario);
        if (userOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        return userOptional.get();
    }

    //métodos crud basicos de user
    //método para cadastrar tipo favorito de comida
    //metodo para cadastar localizacao
    //metodo para cadastrar preco disposto a pagar
    //metodos que virão do microserviço recommendationservice futuramente
}
