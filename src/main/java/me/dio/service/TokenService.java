package me.dio.service;

import me.dio.domain.model.entity.User;

public interface TokenService {
    String generateToken(User user);
    String validarTOken(String token);
}
