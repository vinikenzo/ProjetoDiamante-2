package br.com.restaurante.userservice.dtos.request;

import java.math.BigDecimal;

public record PriceLimitRequest(BigDecimal precoLimite) {
}
