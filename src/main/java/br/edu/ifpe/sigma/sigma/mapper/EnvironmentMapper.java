package br.edu.ifpe.sigma.sigma.mapper;

import br.edu.ifpe.sigma.sigma.dto.environment.EnvironmentRequestDTO;
import br.edu.ifpe.sigma.sigma.dto.environment.EnvironmentResponseDTO;
import br.edu.ifpe.sigma.sigma.entity.Environment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ComponentMapper.class})
public interface EnvironmentMapper {
    EnvironmentResponseDTO toEnvironmentResponseDTO(Environment environment);

    List<EnvironmentResponseDTO> toEnvironmentResponseDTOList(List<Environment> environments);

    @Mapping(target = "components", ignore = true)
    Environment toEnvironment(EnvironmentRequestDTO dto);

    @Mapping(target = "components", ignore = true)
    void updateEnvironmentFromDTO(EnvironmentRequestDTO dto, @MappingTarget Environment environment);
}
