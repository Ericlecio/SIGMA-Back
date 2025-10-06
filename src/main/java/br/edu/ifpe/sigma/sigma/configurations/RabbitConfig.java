package br.edu.ifpe.sigma.sigma.configurations;

import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_REGISTER_CREATED = "sigma-register-created";
    public static final String QUEUE_REGISTER_UPDATE = "sigma-register-update";


    @Bean
    public Declarables queues() {
        return new Declarables(
                new Queue(QUEUE_REGISTER_CREATED, true),
                new Queue(QUEUE_REGISTER_UPDATE, true)
        );
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

