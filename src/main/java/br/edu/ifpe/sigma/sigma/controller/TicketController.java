package br.edu.ifpe.sigma.sigma.controller;

import br.edu.ifpe.sigma.sigma.dto.ReportDTO;
import br.edu.ifpe.sigma.sigma.dto.TicketDTO;
import br.edu.ifpe.sigma.sigma.service.TicketService;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/report")
    public ResponseEntity<ReportDTO> getReport(@Param("startDate") String startDate, @Param("endDate") String endDate) {
        var report = ticketService.getReport(
                java.time.LocalDate.parse(startDate),
                java.time.LocalDate.parse(endDate)
        );
        return ResponseEntity.ok(report);
    }

    @GetMapping("/my-tickets")
    public ResponseEntity<List<TicketDTO>> getMyTickets(Principal principal) {
        return ResponseEntity.ok(ticketService.getTickets(principal.getName()));
    }

}
