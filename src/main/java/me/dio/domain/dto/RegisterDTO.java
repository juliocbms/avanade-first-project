package me.dio.domain.dto;

import me.dio.domain.model.UserRoles;

public record RegisterDTO(String email, String password, UserRoles role) {
}
