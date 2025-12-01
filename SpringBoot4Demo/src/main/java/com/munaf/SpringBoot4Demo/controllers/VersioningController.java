package com.munaf.SpringBoot4Demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class VersioningController {


    @GetMapping(version = "1") // http://localhost:8080/api/v1/test
    public String testV1() {
        return "Hello World! V1";
    }

    @GetMapping(version = "2") // http://localhost:8080/api/v2/test
    public String testV2() {
        return "Hello World! V2";
    }

    @GetMapping(version = "3+") // http://localhost:8080/api/v2/test
    public String testV1Plus() {
        return "Hello World! V3+";
    }

}

//Spring Boot 4 lets you choose:
//
//✓ Path versioning → /api/v1/hello
//✓ Header versioning → API-Version: 1.0
//✓ Query parameter → /hello?v=1