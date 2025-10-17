package br.edu.ifpe.sigma.sigma.service;

import br.edu.ifpe.sigma.sigma.dto.ReportDTO;
import br.edu.ifpe.sigma.sigma.dto.TicketDTO;
import br.edu.ifpe.sigma.sigma.entity.User;
import br.edu.ifpe.sigma.sigma.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;

    public TicketService(final TicketRepository ticketRepository, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
    }

    public ReportDTO getReport(final LocalDate startDate,
                               LocalDate endDate) {
        final LocalDate currentDate = java.time.LocalDate.now();
        if (endDate.isAfter(currentDate)) {
            endDate = currentDate;
        }

        var tickets = ticketRepository.findByCreatedAtBetween(startDate, endDate);
        return ReportDTO.from(tickets);
    }

    public List<TicketDTO> getTickets(final String username) {
        final User user = userService.findByUsername(username);
        final List<TicketDTO> result = ticketRepository.findByCreatedAt(user).stream()
                .map(TicketDTO::from)
                .toList();
        if (result.isEmpty()) {
            throw new RuntimeException("No tickets found for the user.");
        }
        return result;
    }
}
