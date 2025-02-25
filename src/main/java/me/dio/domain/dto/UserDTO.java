package me.dio.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.domain.model.enums.UserRoles;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String email;
    private String nome;
    private  String senha;
    private UserRoles role;
}
