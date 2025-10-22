package br.edu.ifpe.sigma.sigma.mapper;

import br.edu.ifpe.sigma.sigma.dto.component.ComponentRequestDTO;
import br.edu.ifpe.sigma.sigma.dto.component.ComponentResponseDTO;
import br.edu.ifpe.sigma.sigma.entity.Component;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComponentMapper {
    Component toComponent(ComponentRequestDTO dto);

    ComponentResponseDTO toComponentResponseDTO(Component component);

    List<ComponentResponseDTO> toComponentResponseDTOList(List<Component> components);

    void updateComponentFromDTO(ComponentRequestDTO dto, @MappingTarget Component component);

}
