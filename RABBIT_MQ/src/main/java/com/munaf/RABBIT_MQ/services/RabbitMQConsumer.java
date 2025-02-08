package com.munaf.RABBIT_MQ.services;

import com.munaf.RABBIT_MQ.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer { // for receive a message

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }

}
