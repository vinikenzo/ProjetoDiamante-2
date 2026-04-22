package br.com.restaurante.userservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USUARIO_PROJETODIAMANTE")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String localizacao;

    private String tipoComidaFavorita;

    private BigDecimal precoDispostoAPagar;
}

