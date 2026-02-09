package com.munaf.notification_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public static final String USER_TOPIC_NAME = "user-topic";
    public static final String USER_CREATED_TOPIC = "user-created-topic";

}
