package br.edu.ifpe.sigma.sigma.dto;

import br.edu.ifpe.sigma.sigma.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private String registration;
    private String phone;
    private String password;
    private Role role;
}