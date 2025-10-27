package br.edu.ifpe.sigma.sigma.controller;

import br.edu.ifpe.sigma.sigma.dto.component.ComponentRequestDTO;
import br.edu.ifpe.sigma.sigma.dto.component.ComponentResponseDTO;
import br.edu.ifpe.sigma.sigma.entity.Component;
import br.edu.ifpe.sigma.sigma.service.ComponentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/components")
@RequiredArgsConstructor
public class ComponentController {

    private final ComponentService componentService;

    @GetMapping
    public ResponseEntity<List<ComponentResponseDTO>> getAllComponents(){
        return ResponseEntity.ok(componentService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Component> getComponentById(@PathVariable UUID id){
        return ResponseEntity.ok(componentService.findById(id));
    }


    @PostMapping
    public  ResponseEntity<ComponentResponseDTO> createComponent(@Valid @RequestBody ComponentRequestDTO dto){
        ComponentResponseDTO createdComponent = componentService.create(dto);
        return new ResponseEntity<>(createdComponent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComponentResponseDTO> updateComponent(@PathVariable UUID id, @Valid @RequestBody ComponentRequestDTO dto){
        return ResponseEntity.ok(componentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComponent(@PathVariable UUID id){
        componentService.delete(id);
    }
}
