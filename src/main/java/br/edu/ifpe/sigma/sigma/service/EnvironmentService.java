package br.edu.ifpe.sigma.sigma.service;

import br.edu.ifpe.sigma.sigma.dto.environment.EnvironmentRequestDTO;
import br.edu.ifpe.sigma.sigma.dto.environment.EnvironmentResponseDTO;
import br.edu.ifpe.sigma.sigma.entity.Component;
import br.edu.ifpe.sigma.sigma.entity.Environment;
import br.edu.ifpe.sigma.sigma.exception.NotFoundException;
import br.edu.ifpe.sigma.sigma.mapper.EnvironmentMapper;
import br.edu.ifpe.sigma.sigma.repository.ComponentRepository;
import br.edu.ifpe.sigma.sigma.repository.EnvironmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnvironmentService {
    // op serviço pra criar os locais e fazer a consulta o front end precisa saber e inserir locais

    private final EnvironmentRepository environmentRepository;
    private final EnvironmentMapper environmentMapper;
    private final ComponentRepository componentRepository;


    public Environment findById(UUID id) {
        return environmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Environment not found"));
    }

    public List<EnvironmentResponseDTO> findAll(){
        List<Environment> environments = environmentRepository.findAll();
        return environmentMapper.toEnvironmentResponseDTOList(environments);
    }

    public EnvironmentResponseDTO create(EnvironmentRequestDTO dto){
        Environment environment = environmentMapper.toEnvironment(dto);
        Environment savedEnvironment = environmentRepository.save(environment);
        return environmentMapper.toEnvironmentResponseDTO(savedEnvironment);
    }

    public EnvironmentResponseDTO update(UUID id, EnvironmentRequestDTO dto){
        Environment existingEnvironment = findById(id);
        environmentMapper.updateEnvironmentFromDTO(dto, existingEnvironment);
        Environment updatedEnvironment = environmentRepository.save(existingEnvironment);
        return environmentMapper.toEnvironmentResponseDTO(updatedEnvironment);
    }

    public void delete(UUID id){
        Environment environment = findById(id);
        environmentRepository.delete(environment);
    }

    //nao sei se vai funcionar os services abaixo

    public EnvironmentResponseDTO addComponentToEnvironment(UUID environmentId, UUID componentId) {
        Environment environment = findById(environmentId);
        Component component = componentRepository.findById(componentId).orElseThrow(() -> new NotFoundException("Component not found"));
        environment.getComponents().add(component);
        Environment updatedEnvironment = environmentRepository.save(environment);
        return environmentMapper.toEnvironmentResponseDTO(updatedEnvironment);
    }

    public EnvironmentResponseDTO removeComponentFromEnvironment(UUID environmentId, UUID componentId) {
        Environment environment = findById(environmentId);
        Component component = componentRepository.findById(componentId).orElseThrow(() -> new NotFoundException("Component not found"));
        //nao deleta o componente, so define o environment_id como null
        environment.getComponents().remove(component);

        Environment updatedEnvironment = environmentRepository.save(environment);
        return environmentMapper.toEnvironmentResponseDTO(updatedEnvironment);
    }
}
