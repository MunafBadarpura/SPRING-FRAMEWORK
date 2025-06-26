package com.munaf.A09_SPRING_WEB_3.controllers;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/test")
@RestController
public class TestController {

    @RequestMapping()
    public String getTest() {
        System.out.println("RUUNEND");
        return "requestMapping";
    }

    @GetMapping()
    public String getTest2() {
        System.out.println("RUUNENDGETTTT");
        return "getMapping";
    }

    @PostMapping()
    public String getTest2232() {
        return "PostMapping";
    }


    @PatchMapping()
    public String getTest32322() {
        return "PatchMapping";
    }


    @PutMapping()
    public String getTest322() {
        return "PutMapping";
    }

    @DeleteMapping()
    public String getTest32() {
        return "DeleteMapping";
    }


}
