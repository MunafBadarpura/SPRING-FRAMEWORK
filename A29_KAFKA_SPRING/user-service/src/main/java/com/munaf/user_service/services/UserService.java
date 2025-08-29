package com.munaf.user_service.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Value("${kafka.user.topic.name}")
    private String USER_TOPIC_NAME;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public UserService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String sendMessage(String message) {
        for (int i = 1; i <= 1000; i++) {
            kafkaTemplate.send(USER_TOPIC_NAME, message+i);
        }
        return "Message In Queue";
    }
}
