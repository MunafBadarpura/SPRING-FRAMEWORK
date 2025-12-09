package com.munaf.A30_TOOL_CALLING.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {


    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://api.weatherapi.com/v1")
                .build();
    }

}
