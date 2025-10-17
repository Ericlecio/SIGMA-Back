package br.edu.ifpe.sigma.sigma.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ticket_files")
public class TicketFile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Column(length = 2024)
    private String contentUrl;
    @ManyToOne
    private Ticket ticket;
}
