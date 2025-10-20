package br.edu.ifpe.sigma.sigma.service;

import br.edu.ifpe.sigma.sigma.entity.Environment;
import br.edu.ifpe.sigma.sigma.entity.Status;
import br.edu.ifpe.sigma.sigma.entity.Ticket;
import br.edu.ifpe.sigma.sigma.dto.TicketRequest;
import br.edu.ifpe.sigma.sigma.dto.TicketResponse;
import br.edu.ifpe.sigma.sigma.entity.User;
import br.edu.ifpe.sigma.sigma.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final EnvironmentService environmentService;

    public TicketResponse create(final TicketRequest request) {

        final Environment environment = environmentService.findById(request.getEnvironment());
        final User createdBy = userService.findById(request.getCreatedBy());

        Ticket ticket = Ticket.builder()
                .description(request.getDescription())
                .status(Status.OPEN)
                .priority(request.getPriority())
                .problemType(request.getProblemType())
                .environment(environment)
                .createdBy(request.getCreatedBy())
                .ticketFile(request.getTicketFile())
                .build();

        Ticket saved = ticketRepository.save(ticket);
        return TicketResponse.fromEntity(saved);
    }

    public List<TicketResponse> findAll() {
        return ticketRepository.findAll().stream()
                .map(TicketResponse::fromEntity)
                .toList();
    }

    public TicketResponse findById(UUID id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
        return TicketResponse.fromEntity(ticket);
    }

    public TicketResponse update(UUID id, TicketRequest request) {
        Ticket existing = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found"));

        existing.setDescription(request.getDescription());
        existing.setStatus(request.getStatus());
        existing.setPriority(request.getPriority());
        existing.setProblemType(request.getProblemType());
        existing.setEnvironment(request.getEnvironment());
        existing.setAssignedTo(request.getAssignedTo());
        existing.setTicketFile(request.getTicketFile());

        Ticket updated = ticketRepository.save(existing);
        return TicketResponse.fromEntity(updated);
    }

    public void delete(UUID id) {
        if (!ticketRepository.existsById(id))
            throw new EntityNotFoundException("Ticket not found");
        ticketRepository.deleteById(id);
    }
}