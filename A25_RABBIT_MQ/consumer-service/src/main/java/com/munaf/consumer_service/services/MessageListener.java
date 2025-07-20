package com.munaf.consumer_service.services;

import com.munaf.consumer_service.configs.RabbitMqConfig;
import com.munaf.consumer_service.dto.OrderDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    private final EmailService emailService;

    public MessageListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitMqConfig.EMAIL_QUEUE_NAME)
    public void consumeMessageFromQueue(OrderDTO orderDTO) {
        emailService.sendEmail(orderDTO);
    }

}
