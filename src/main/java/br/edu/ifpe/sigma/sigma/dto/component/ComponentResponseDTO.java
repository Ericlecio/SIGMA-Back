package br.edu.ifpe.sigma.sigma.dto.component;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComponentResponseDTO {
    private UUID id;
    private String code;
    private String mark;
    private String model;
    private String description;
}
