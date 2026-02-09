package com.munaf.user_service.services;

import com.munaf.user_service.config.KafkaConfig;
import com.munaf.user_service.entity.UserEntity;
import com.munaf.user_service.event.UserCreatedEvent;
import com.munaf.user_service.repositories.UserRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, UserCreatedEvent> userCreatedEventKafkaTemplate;
    private final UserRepository userRepository;

    public UserService(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, UserCreatedEvent> userCreatedEventKafkaTemplate, UserRepository userRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.userCreatedEventKafkaTemplate = userCreatedEventKafkaTemplate;
        this.userRepository = userRepository;
    }

    public String sendMessage(String message) {
        for (int i = 1; i <= 1000; i++) {
            kafkaTemplate.send(KafkaConfig.USER_TOPIC_NAME, message+i);
        }
        return "Message In Queue";
    }

    @Transactional
    public String createUser(UserEntity userEntity) {
        try {

            // save user
            UserEntity savedUser = userRepository.save(userEntity);

            // create event
            UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder()
                    .id(savedUser.getId())
                    .name(savedUser.getName())
                    .email(savedUser.getEmail())
                    .build();

            // produce event to kafka
            userCreatedEventKafkaTemplate.send(KafkaConfig.USER_CREATED_TOPIC, userCreatedEvent);

            return "User Created Successfully ✅";
        } catch (Exception e) {
            return "Error in creating user " + e.getMessage();
        }
    }
}

// kafkaTemplate.send(KafkaConfig.USER_TOPIC_NAME, ""+i%2, message+i);
// if the producer is providing key then the partition is calculated based on key
// if the key is not provided then the partition is calculated based on hash of message