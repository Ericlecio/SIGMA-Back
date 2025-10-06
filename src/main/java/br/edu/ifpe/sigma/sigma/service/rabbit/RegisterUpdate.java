package br.edu.ifpe.sigma.sigma.service.rabbit;

import br.edu.ifpe.sigma.sigma.configurations.RabbitConfig;
import br.edu.ifpe.sigma.sigma.dto.UserUpdateDTO;
import br.edu.ifpe.sigma.sigma.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RegisterUpdate {

    private final UserRepository userRepository;

    public RegisterUpdate(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_REGISTER_UPDATE)
    public void receiveMessage(UserUpdateDTO message) {
        var user = userRepository.findByCorrelationId(message.getId());
        if (user == null)
            return;
        user.setName(message.getName());
        user.setEmail(message.getEmail());
        user.setIsActive(message.getIsActive());
        user.setRole(message.getRole());
        user.setPhone(message.getPhone());
        userRepository.save(user);
    }
}