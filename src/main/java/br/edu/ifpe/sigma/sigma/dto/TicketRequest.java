package br.edu.ifpe.sigma.sigma.dto;

import br.edu.ifpe.sigma.sigma.entity.*;
import lombok.Data;

import java.util.List;

@Data
public class TicketRequest {
    private String description;
    private Status status;
    private Priority priority;
    private ProblemType problemType;
    private Environment environment;
    private User assignedTo;
    private User createdBy;
    private List<TicketFile> ticketFile;
}
