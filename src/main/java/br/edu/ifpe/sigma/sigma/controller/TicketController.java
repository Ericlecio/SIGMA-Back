package br.edu.ifpe.sigma.sigma.controller;

import br.edu.ifpe.sigma.sigma.dto.ReportDTO;
import br.edu.ifpe.sigma.sigma.dto.TicketDTO;
import br.edu.ifpe.sigma.sigma.dto.TicketRequest;
import br.edu.ifpe.sigma.sigma.dto.TicketResponse;
import br.edu.ifpe.sigma.sigma.entity.User;
import br.edu.ifpe.sigma.sigma.service.TicketService;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponse> create(@RequestBody TicketRequest request) {
        return ResponseEntity.ok(ticketService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> findAll() {
        return ResponseEntity.ok(ticketService.findAll());
    }

    @GetMapping("/report")
    public ResponseEntity<ReportDTO> getReport(@Param("startDate") String startDate, @Param("endDate") String endDate) {
        var report = ticketService.getReport(
                java.time.LocalDate.parse(startDate),
                java.time.LocalDate.parse(endDate)
        );
        return ResponseEntity.ok(report);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(ticketService.findById(id));
    }

    @GetMapping("/my-tickets")
    public ResponseEntity<List<TicketDTO>> getMyTickets(Principal principal) {
        User user = (User) ((org.springframework.security.core.Authentication) principal).getPrincipal();
        Logger.getLogger(TicketController.class.getName()).log(Level.INFO, "getMyTickets user: {0}", user.getEmail());
        return ResponseEntity.ok(ticketService.getTickets(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponse> update(@PathVariable UUID id, @RequestBody TicketRequest request) {
        return ResponseEntity.ok(ticketService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
