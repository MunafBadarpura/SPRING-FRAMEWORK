package com.munaf.notification_service.consumer;

import com.munaf.notification_service.config.KafkaConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserKafkaConsumer {

    // @KafkaListener will not work without consumer group id (we define this in properties/yaml)

    @KafkaListener(topics = KafkaConfig.USER_TOPIC_NAME)
    public void consumeUserTopic1(String message) {
        System.out.println("consumeUserTopic1 received message: " + message);
    }


//    @KafkaListener(topics = KafkaConfig.USER_TOPIC_NAME)
//    public void consumeUserTopic2(String message) {
//        System.out.println("consumeUserTopic2 received message: " + message);
//    }
//
//
//    @KafkaListener(topics = KafkaConfig.USER_TOPIC_NAME)
//    public void consumeUserTopic3(String message) {
//        System.out.println("consumeUserTopic3 received message: " + message);
//    }
//
//    @KafkaListener(topics = KafkaConfig.USER_TOPIC_NAME)
//    public void consumeUserTopic4(String message) {
//        System.out.println("consumeUserTopic4 received message: " + message);
//    }

}
