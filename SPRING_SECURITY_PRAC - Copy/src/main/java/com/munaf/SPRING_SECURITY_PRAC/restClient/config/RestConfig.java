package com.munaf.SPRING_SECURITY_PRAC.restClient.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {


    @Bean
    @Qualifier("todosRestClient")
    public RestClient getTodosRestClient() {
        return RestClient
                .builder()
                .baseUrl("https://67a6e668510789ef0dfc6baa.mockapi.io/todos/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


}
