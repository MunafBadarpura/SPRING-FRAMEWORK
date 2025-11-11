package com.munaf.A32_SPRING_BOOT_EVENTS.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

@Configuration
@Slf4j
public class EventConfig {

    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticast = new SimpleApplicationEventMulticaster();

        // handler global errors for events
        eventMulticast.setErrorHandler(error -> {
            log.info("Error occurred: {}", error.getMessage());
        });

        return eventMulticast;
    }

}
