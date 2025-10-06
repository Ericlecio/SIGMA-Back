package br.edu.ifpe.sigma.sigma.service.rabbit;

import br.edu.ifpe.sigma.sigma.configurations.RabbitConfig;
import br.edu.ifpe.sigma.sigma.dto.UserDTO;
import br.edu.ifpe.sigma.sigma.entity.Role;
import br.edu.ifpe.sigma.sigma.mapper.UserMapper;
import br.edu.ifpe.sigma.sigma.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RegisterCreated {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public RegisterCreated(final UserRepository userRepository,
                           final UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_REGISTER_CREATED)
    public void receiveMessage(UserDTO message) {
        if (userRepository.existsByCorrelationId(message.getId()))
            return;
        var user = userMapper.toEntity(message);
        user.setIsActive(true);
        if (message.getRole() == null) {
            user.setRole(Role.STUDENT);
        }
        userRepository.save(user);
    }
}