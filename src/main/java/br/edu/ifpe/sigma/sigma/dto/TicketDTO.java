package br.edu.ifpe.sigma.sigma.dto;

import br.edu.ifpe.sigma.sigma.entity.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDTO {
    private UUID id;
    private String description;
    private Status status;
    private Environment environment;
    private Priority priority;
    private ProblemType problemType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User assignedTo;
    private User createdBy;
    private List<TicketFile> ticketFile;

    public static  TicketDTO from(Ticket ticket) {
        return TicketDTO.builder()
                .id(ticket.getId())
                .description(ticket.getDescription())
                .status(ticket.getStatus())
                .environment(ticket.getEnvironment())
                .priority(ticket.getPriority())
                .problemType(ticket.getProblemType())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .assignedTo(ticket.getAssignedTo())
                .createdBy(ticket.getCreatedBy())
                .ticketFile(ticket.getTicketFile())
                .build();
    }
}
