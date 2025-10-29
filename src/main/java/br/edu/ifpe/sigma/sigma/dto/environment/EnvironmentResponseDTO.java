package br.edu.ifpe.sigma.sigma.dto.environment;

import br.edu.ifpe.sigma.sigma.dto.component.ComponentResponseDTO;
import br.edu.ifpe.sigma.sigma.entity.EnvironmentCategory;
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
    private EnvironmentCategory category;
    private List<ComponentResponseDTO> components;
}