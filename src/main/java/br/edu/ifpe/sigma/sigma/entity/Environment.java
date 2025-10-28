package br.edu.ifpe.sigma.sigma.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "environments")
public class Environment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String block;
    private String room;
    @OneToMany(mappedBy = "environment", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Component> components = new ArrayList<>();
}
