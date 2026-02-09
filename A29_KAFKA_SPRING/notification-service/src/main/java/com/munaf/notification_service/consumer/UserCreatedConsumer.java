package com.munaf.notification_service.consumer;

import com.munaf.notification_service.config.KafkaConfig;
import com.munaf.user_service.event.UserCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedConsumer {

    @KafkaListener(topics = KafkaConfig.USER_CREATED_TOPIC)
    public void consumeUserCreatedTopic(UserCreatedEvent userCreatedEvent) {
        System.out.println("consumeUserCreatedTopic received userCreatedEvent: " + userCreatedEvent);
    }

}
