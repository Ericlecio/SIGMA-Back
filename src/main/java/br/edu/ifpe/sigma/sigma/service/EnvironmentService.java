package br.edu.ifpe.sigma.sigma.service;

import br.edu.ifpe.sigma.sigma.entity.Environment;
import br.edu.ifpe.sigma.sigma.exception.NotFoundException;
import br.edu.ifpe.sigma.sigma.repository.EnvironmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnvironmentService {
    // op serviço pra criar os locais e fazer a consulta o front end precisa saber e inserir locais

    private final EnvironmentRepository environmentRepository;

    public Environment findById(UUID id) {
        return environmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Environment not found"));
    }
}
