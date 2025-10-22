package br.edu.ifpe.sigma.sigma.service;

import br.edu.ifpe.sigma.sigma.dto.component.ComponentRequestDTO;
import br.edu.ifpe.sigma.sigma.dto.component.ComponentResponseDTO;
import br.edu.ifpe.sigma.sigma.entity.Component;
import br.edu.ifpe.sigma.sigma.exception.NotFoundException;
import br.edu.ifpe.sigma.sigma.mapper.ComponentMapper;
import br.edu.ifpe.sigma.sigma.repository.ComponentRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComponentService {

    private final ComponentRepository componentRepository;
    private final ComponentMapper componentMapper;

    //helper
    protected Component findComponentById(UUID id) {
        return componentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Componente não encontrado"));
    }


    public List<ComponentResponseDTO> findAll(){
        List<Component> components = componentRepository.findAll();
        return componentMapper.toComponentResponseDTOList(components);
    }

    public ComponentResponseDTO findById(UUID id){
        Component component = componentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Component not found"));
        return componentMapper.toComponentResponseDTO(component);
    }

    public ComponentResponseDTO create(ComponentRequestDTO dto){
        Component component = componentMapper.toComponent(dto);
        Component savedComponent = componentRepository.save(component);
        return componentMapper.toComponentResponseDTO(savedComponent);
    }

    public ComponentResponseDTO update(UUID id, ComponentRequestDTO dto) {
        Component existingComponent = findComponentById(id);
        componentMapper.updateComponentFromDTO(dto, existingComponent);
        Component updatedComponent = componentRepository.save(existingComponent);
        return componentMapper.toComponentResponseDTO(updatedComponent);
    }

    public void delete (UUID id){ //testar pra ver se ta ok
        Component component = componentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Component not found"));
        componentRepository.delete(component);
    }
}
