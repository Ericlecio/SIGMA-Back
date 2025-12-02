package br.edu.ifpe.sigma.sigma.entity;

import jakarta.persistence.*;
import lombok.*;

import java.security.Principal;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID correlationId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 150, unique = true)
    private String email;

    @Column(length = 20, unique = true)
    private String registration;

    private Boolean isActive;

    private String password;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;
}
