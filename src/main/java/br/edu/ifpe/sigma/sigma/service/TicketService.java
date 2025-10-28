package br.edu.ifpe.sigma.sigma.service;

import br.edu.ifpe.sigma.sigma.entity.*;
import br.edu.ifpe.sigma.sigma.dto.TicketRequest;
import br.edu.ifpe.sigma.sigma.dto.TicketResponse;
import br.edu.ifpe.sigma.sigma.dto.ReportDTO;
import br.edu.ifpe.sigma.sigma.dto.TicketDTO;
import br.edu.ifpe.sigma.sigma.exception.NotFoundException;
import br.edu.ifpe.sigma.sigma.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final EnvironmentService environmentService;

    public TicketResponse findById(UUID id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
        return TicketResponse.fromEntity(ticket);
    }

    public TicketResponse create(final TicketRequest request) {
        final Environment environment = environmentService.findById(request.getEnvironment());
        final User createdBy = userService.findById(request.getCreatedBy());
        final Component component = environment.getComponents()
                .stream()
                .filter(x -> x.getId().equals(request.getComponent()))
                .findFirst()
                .orElse(null);

        Ticket ticket = Ticket.builder()
                .description(request.getDescription())
                .status(Status.OPEN)
                .priority(request.getPriority())
                .problemType(request.getProblemType())
                .environment(environment)
                .component(component)
                .createdBy(createdBy)
                .ticketFile(request.getTicketFile())
                .build();

        Ticket saved = ticketRepository.save(ticket);
        return TicketResponse.fromEntity(saved);
    }

    public ReportDTO getReport(final LocalDate startDate,
                               LocalDate endDate) {
        final LocalDate currentDate = java.time.LocalDate.now();
        if (endDate.isAfter(currentDate)) {
            endDate = currentDate;
        }
        var tickets = ticketRepository.findByCreatedAtBetween(startDate, endDate); // isso e o relatorio
        return ReportDTO.from(tickets);
    }

    public List<TicketResponse> findAll() {
        return ticketRepository.findAll().stream()
                .map(TicketResponse::fromEntity)
                .toList();
    }

    public List<TicketDTO> getTickets(User user) {
        final List<TicketDTO> result = ticketRepository.findByCreatedBy(user).stream()
                .map(TicketDTO::from)
                .toList();
        if (result.isEmpty()) {
            throw new NotFoundException("No tickets found for the user.");
        }
        return result;
    }



    public TicketResponse update(UUID id, TicketRequest request) {
        Ticket existing = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found"));

        final User assignedTo = userService.findById(request.getAssignedTo());

        existing.setDescription(request.getDescription());
        existing.setStatus(request.getStatus());
        existing.setPriority(request.getPriority());
        existing.setProblemType(request.getProblemType());
        existing.setAssignedTo(assignedTo);
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