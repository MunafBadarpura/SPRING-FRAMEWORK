package com.munaf.RABBIT_MQ.services;

import com.munaf.RABBIT_MQ.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProcedure { // for send a message

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUTING_KEY, message);
        System.out.println("Sent message: " + message);
    }

}
