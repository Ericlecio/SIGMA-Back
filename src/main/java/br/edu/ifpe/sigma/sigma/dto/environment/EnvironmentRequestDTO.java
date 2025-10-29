package br.edu.ifpe.sigma.sigma.dto.environment;

import br.edu.ifpe.sigma.sigma.dto.component.ComponentRequestDTO;
import br.edu.ifpe.sigma.sigma.entity.EnvironmentCategory;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnvironmentRequestDTO {
    private String name;
    private String block;
    private EnvironmentCategory category;
}