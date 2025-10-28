package br.edu.ifpe.sigma.sigma.controller;

import br.edu.ifpe.sigma.sigma.dto.environment.EnvironmentRequestDTO;
import br.edu.ifpe.sigma.sigma.dto.environment.EnvironmentResponseDTO;
import br.edu.ifpe.sigma.sigma.entity.Environment;
import br.edu.ifpe.sigma.sigma.service.EnvironmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/environments")
@RequiredArgsConstructor
public class EnvironmentController {

    private final EnvironmentService environmentService;

    @GetMapping
    public ResponseEntity<List<EnvironmentResponseDTO>> getAllEnvironments(){
        return ResponseEntity.ok(environmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Environment> getEnvironmentById(@PathVariable UUID id) {
        return ResponseEntity.ok(environmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EnvironmentResponseDTO> createEnvironment(
            @Valid @RequestBody EnvironmentRequestDTO dto) {
        EnvironmentResponseDTO createdEnvironment = environmentService.create(dto);
        return new ResponseEntity<>(createdEnvironment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnvironmentResponseDTO> updateEnvironment(
            @PathVariable UUID id,
            @Valid @RequestBody EnvironmentRequestDTO dto) {
        return ResponseEntity.ok(environmentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEnvironment(@PathVariable UUID id) {
        environmentService.delete(id);
    }

    //testar esses endpoints de addComponent e removeComponent

    @PostMapping("/{environmentId}/components/{componentId}")
    public ResponseEntity<EnvironmentResponseDTO> addComponent(
            @PathVariable UUID environmentId,
            @PathVariable UUID componentId) {
        return ResponseEntity.ok(environmentService.addComponentToEnvironment(environmentId, componentId));
    }

    @DeleteMapping("/{environmentId}/components/{componentId}")
    public ResponseEntity<EnvironmentResponseDTO> removeComponent(
            @PathVariable UUID environmentId,
            @PathVariable UUID componentId) {
        return ResponseEntity.ok(environmentService.removeComponentFromEnvironment(environmentId, componentId));
    }
}

