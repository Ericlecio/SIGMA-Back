package br.edu.ifpe.sigma.sigma.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID correlationId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 150, unique = true)
    private String email;

    @Column(nullable = false, length = 20, unique = true)
    private String registration;

    private Boolean isActive;

    private String type;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;
}
