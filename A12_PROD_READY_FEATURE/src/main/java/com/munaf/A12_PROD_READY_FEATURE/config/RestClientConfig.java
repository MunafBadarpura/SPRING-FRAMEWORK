package com.munaf.A12_PROD_READY_FEATURE.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
@Slf4j
public class RestClientConfig {

    @Value("${employeeService.base.url}") //defined in application.properties : http://localhost:8080/employee
    private String BASE_URL;

    @Bean
    @Qualifier("EmployeeServiceRestClient")
    RestClient getEmployeeServiceRestClient(){
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, (req, res) -> {
                    log.error("ERROR : {}", new String(res.getBody().readAllBytes()));
                    throw new RuntimeException("Internal Server Error");
                })
                .build();
    }


    // ------------------------------

    @Bean
    @Qualifier("testingRestClient")
    RestClient getTestingRestClient(){
        return RestClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
