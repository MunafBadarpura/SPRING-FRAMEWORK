package com.munaf.A16_SPRING_TESTING.controller;

import com.munaf.A16_SPRING_TESTING.TestContainerConfigurations;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@AutoConfigureWebTestClient(timeout = "100000") // this will autoconfigure webTestClient so we can autowire
@Import(TestContainerConfigurations.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractIntegrationTest {
}
