package br.edu.ifpe.sigma.sigma.dto.component;

import lombok.*;

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
}