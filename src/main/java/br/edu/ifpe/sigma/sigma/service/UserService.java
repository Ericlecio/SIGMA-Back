package br.edu.ifpe.sigma.sigma.service;

import br.edu.ifpe.sigma.sigma.entity.User;
import br.edu.ifpe.sigma.sigma.exception.NotFoundException;
import br.edu.ifpe.sigma.sigma.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found."));
    }

    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found."));
    }
}
