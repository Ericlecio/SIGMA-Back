package br.edu.ifpe.sigma.sigma.dto;

import br.edu.ifpe.sigma.sigma.entity.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class TicketResponse {
    private UUID id;
    private String description;
    private Status status;
    private Priority priority;
    private ProblemType problemType;
    private Environment environment;
    private User assignedTo;
    private User createdBy;
    private List<TicketFile> ticketFile;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TicketResponse fromEntity(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .description(ticket.getDescription())
                .status(ticket.getStatus())
                .priority(ticket.getPriority())
                .problemType(ticket.getProblemType())
                .environment(ticket.getEnvironment())
                .assignedTo(ticket.getAssignedTo())
                .createdBy(ticket.getCreatedBy())
                .ticketFile(ticket.getTicketFile())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .build();
    }
}
