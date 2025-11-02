package br.edu.ifpe.sigma.sigma.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private EnvironmentCategory category;
    @JsonManagedReference
    @OneToMany(mappedBy = "environment", cascade = CascadeType.ALL)
    private List<Component> components = new ArrayList<>();
}
