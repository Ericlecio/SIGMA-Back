package br.edu.ifpe.sigma.sigma.repository;

import br.edu.ifpe.sigma.sigma.entity.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnvironmentRepository extends JpaRepository<Environment, UUID> {
}
