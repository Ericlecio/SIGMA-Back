package br.edu.ifpe.sigma.sigma.dto.environment;

import br.edu.ifpe.sigma.sigma.dto.component.ComponentResponseDTO;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnvironmentResponseDTO {
    private UUID id;
    private String name;
    private String block;
    private String room;
    private List<ComponentResponseDTO> components;
}