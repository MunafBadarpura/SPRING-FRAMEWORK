package com.munaf.RABBIT_MQ.controllers;

import com.munaf.RABBIT_MQ.services.RabbitMQProcedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

    @Autowired
    private RabbitMQProcedure rabbitMQProcedure;

    @GetMapping("/send/{message}")
    public String sendMessage(@PathVariable String message) {
        rabbitMQProcedure.sendMessage(message);
        return "Message sent: " + message;
    }

}
