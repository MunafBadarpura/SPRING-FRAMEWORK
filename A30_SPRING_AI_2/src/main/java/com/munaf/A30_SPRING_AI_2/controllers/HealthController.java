package com.munaf.A30_SPRING_AI_2.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @RequestMapping("/")
    public String health() {
        return "Service is up and running";
    }

}
