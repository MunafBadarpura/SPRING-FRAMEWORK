package com.munaf.SPRING_SECURITY_PRAC.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hello")
    public String hello() {
        return "HELLO PAGE";
    }

    @GetMapping("/home")
    public String home() {
        return "HOME PAGE";
    }

}
