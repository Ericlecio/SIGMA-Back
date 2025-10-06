package br.edu.ifpe.sigma.sigma.mapper;

import br.edu.ifpe.sigma.sigma.dto.UserDTO;
import br.edu.ifpe.sigma.sigma.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "correlationId", source = "id")
    @Mapping(target = "isActive", ignore = true)
    User toEntity(UserDTO dto);
}