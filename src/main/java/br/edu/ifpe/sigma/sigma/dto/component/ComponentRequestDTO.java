package br.edu.ifpe.sigma.sigma.dto.component;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComponentRequestDTO {
    private String code;
    private String mark;
    private String model;
    private String description;
    private UUID environmentId;
}