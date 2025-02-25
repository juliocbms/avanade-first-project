package me.dio.service;

import me.dio.domain.model.entity.User;

import java.util.Optional;

public interface UserService {

    User autenticar(String email, String senha);

    User salvarUsuario(User usuario);

    void validarEmail(String email);

    Optional<User> obterPorId(Long id);
}
