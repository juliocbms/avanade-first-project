package me.dio.domain.dto;

import me.dio.domain.model.enums.UserRoles;

public record RegisterDTO(String email, String password, UserRoles role) {
}
