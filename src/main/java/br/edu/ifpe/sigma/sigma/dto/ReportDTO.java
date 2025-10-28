package br.edu.ifpe.sigma.sigma.dto;

import br.edu.ifpe.sigma.sigma.entity.Status;
import br.edu.ifpe.sigma.sigma.entity.Ticket;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDTO {
    private long totalTickets;
    private Iterable<StatusCount> status;
    private Iterable<EnvironmentCount> environment;
    private Iterable<PriorityCount> priority;

    public static ReportDTO from(List<Ticket> tickets) {
        return ReportDTO
                .builder()
                .totalTickets(tickets.size())
                .status(setStatusCounts(tickets))
                .environment(setEnvironmentCounts(tickets))
                .priority(setPriorityCounts(tickets))
                .build();
    }

    private static Iterable<StatusCount> setStatusCounts(List<Ticket> tickets) {
        return tickets.stream()
                .map(Ticket::getStatus)
                .distinct()
                .map(status -> new StatusCount(
                        status,
                        tickets.stream().filter(ticket -> ticket.getStatus() == status).count()
                ))
                .toList();
    }

    private static  Iterable<EnvironmentCount> setEnvironmentCounts(List<Ticket> tickets) {
        return tickets.stream()
                .map(ticket -> ticket.getEnvironment().getName())
                .distinct()
                .map(environment -> new EnvironmentCount(
                        environment,
                        tickets.stream().filter(ticket -> ticket.getEnvironment().getName().equals(environment)).count()
                ))
                .toList();
    }

    private static  Iterable<PriorityCount> setPriorityCounts(List<Ticket> tickets) {
        return tickets.stream()
                .map(ticket -> ticket.getPriority().name())
                .distinct()
                .map(priority -> new PriorityCount(
                        priority,
                        tickets.stream().filter(ticket -> ticket.getPriority().name().equals(priority)).count()
                ))
                .toList();
    }
}

@Getter
@Setter
class StatusCount {
    private Status status;
    private long count;

    public StatusCount(Status status, long count) {
        this.status = status;
        this.count = count;
    }

}

@Getter
@Setter
class EnvironmentCount {
    private String environment;
    private long count;

    public EnvironmentCount(String environment, long count) {
        this.environment = environment;
        this.count = count;
    }

}

@Getter
@Setter
class PriorityCount {
    private String priority;
    private long count;

    public PriorityCount(String priority, long count) {
        this.priority = priority;
        this.count = count;
    }

}
