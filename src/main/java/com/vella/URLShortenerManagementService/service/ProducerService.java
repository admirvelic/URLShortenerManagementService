package com.vella.URLShortenerManagementService.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue}")
    String queueName;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(queueName, message);
        log.info("Sent <" + message + ">");
    }
}
