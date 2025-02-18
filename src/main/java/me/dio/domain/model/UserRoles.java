package me.dio.domain.model;

public enum UserRoles {

    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRoles(String role) { // Nome do construtor corrigido
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
