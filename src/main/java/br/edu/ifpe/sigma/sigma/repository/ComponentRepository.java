package br.edu.ifpe.sigma.sigma.repository;

import br.edu.ifpe.sigma.sigma.entity.Component;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComponentRepository extends JpaRepository<Component, UUID> {
}
