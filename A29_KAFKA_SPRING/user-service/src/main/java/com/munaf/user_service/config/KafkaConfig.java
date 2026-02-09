package com.munaf.user_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public static final String USER_TOPIC_NAME = "user-topic";
    public static final String USER_CREATED_TOPIC = "user-created-topic";


    @Bean
    public NewTopic userTopic() {
        return new NewTopic(USER_TOPIC_NAME, 3, (short) 1);
    }

    @Bean
    public NewTopic userCreatedTopic() {
        return new NewTopic(USER_CREATED_TOPIC, 3, (short) 1);
    }

}

// topic is like a folder where messages are stored (e.g. order topic will store order related messages)
// partition is like a sub folder where messages are stored (e.g. order topic can have 3 partitions)
// replicationFactor is like a backup of partition in different nodes (e.g. if 1st node fails then 2nd node will take over)