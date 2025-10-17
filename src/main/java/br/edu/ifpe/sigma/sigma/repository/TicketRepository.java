package br.edu.ifpe.sigma.sigma.repository;

import br.edu.ifpe.sigma.sigma.entity.Ticket;
import br.edu.ifpe.sigma.sigma.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    List<Ticket> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate);

    Collection<Ticket> findByCreatedAt(User user);
}
