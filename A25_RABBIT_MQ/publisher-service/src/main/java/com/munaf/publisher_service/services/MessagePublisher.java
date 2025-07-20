package com.munaf.publisher_service.services;

import com.munaf.publisher_service.configs.RabbitMqConfig;
import com.munaf.publisher_service.dto.OrderDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendEmail(OrderDTO orderDTO) {
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.EMAIL_EXCHANGE_NAME,
                RabbitMqConfig.EMAIL_ROUTING_KEY,
                orderDTO
        );
        System.out.println("Message sent: " + orderDTO);
    }


}
