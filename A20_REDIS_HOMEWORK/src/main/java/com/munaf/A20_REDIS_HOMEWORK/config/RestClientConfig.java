package com.munaf.A20_REDIS_HOMEWORK.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {



    final String URL = "http://api.weatherapi.com/v1/current.json";

    @Bean
    public RestClient weatherRestClient() {
        return RestClient.builder()
                .baseUrl(URL)
                .build();
    }

}
