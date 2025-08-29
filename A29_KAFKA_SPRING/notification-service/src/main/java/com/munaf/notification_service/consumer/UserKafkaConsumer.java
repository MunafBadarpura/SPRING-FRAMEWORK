package com.munaf.notification_service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserKafkaConsumer {

    @KafkaListener(topics = "user-topic")
    public void consumeUserTopic1(String message) {
        System.out.println("Received message: " + message);
    }


    @KafkaListener(topics = "user-topic")
    public void consumeUserTopic2(String message) {
        System.out.println("Received message: " + message);
    }


    @KafkaListener(topics = "user-topic")
    public void consumeUserTopic3(String message) {
        System.out.println("Received message: " + message);
    }

}
