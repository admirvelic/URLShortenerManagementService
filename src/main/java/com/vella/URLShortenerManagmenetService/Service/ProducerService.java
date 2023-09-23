package com.vella.URLShortenerManagmenetService.Service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue}")
    String queueName;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
