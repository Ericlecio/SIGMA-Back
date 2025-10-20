package br.edu.ifpe.sigma.sigma.dto;

import br.edu.ifpe.sigma.sigma.entity.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class TicketRequest {
    private String description;
    private Status status;// todo chamado vai começar como OPEN
    private Priority priority;
    private ProblemType problemType;
    private UUID environment;
    private UUID assignedTo; // optional normalmente um chamdo se inicia sem ninguem assinalado
    private UUID createdBy;
    private List<TicketFile> ticketFile;
}
