package com.munaf.A17_AOP_HOMEWORK.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {



    @GetMapping("exception")
    public String getException() {
        throw new RuntimeException("Test Runtime Exception");
    }

    @GetMapping("token/{jwtToken}")
    public String jwtCheck(@PathVariable String jwtToken) {
        return "VALID TOKEN YOU CAN ENTER";
    }

}
