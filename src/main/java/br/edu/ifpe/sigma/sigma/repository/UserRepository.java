package br.edu.ifpe.sigma.sigma.repository;

import br.edu.ifpe.sigma.sigma.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByCorrelationId(UUID correlationId);
    Boolean existsByCorrelationId(UUID correlationId);
}
