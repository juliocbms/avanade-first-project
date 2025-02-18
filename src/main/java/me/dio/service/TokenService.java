package me.dio.service;

import me.dio.domain.model.User;

public interface TokenService {
    String generateToken(User user);
    String validarTOken(String token);
}
