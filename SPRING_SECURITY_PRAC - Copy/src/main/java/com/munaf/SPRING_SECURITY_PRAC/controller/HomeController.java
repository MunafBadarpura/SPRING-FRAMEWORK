package com.munaf.SPRING_SECURITY_PRAC.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "HOME PAGE";
    }

    @GetMapping("/success")
    public String success() {
        return "Successfully Logged In";
    }

    @GetMapping("/post")
    public String getPost() {
        return "GET MAPPING POST";
    }

    @PostMapping("/post")
    public String postPost() {
        return "POST MAPPING POST";
    }

    @PutMapping("/post")
    public String putPost() {
        return "PUT MAPPING POST";
    }

    @DeleteMapping("/post")
    public String deletePost() {
        return "DELETE MAPPING POST";
    }


    @GetMapping("/user")
    public String getUserMapping() {
        return "GET MAPPING USER";
    }

    @PostMapping("/user")
    public String postUser() {
        return "POST MAPPING USER";
    }

    @PutMapping("/user")
    public String putUser() {
        return "PUT MAPPING USER";
    }

    @DeleteMapping("/user")
    public String deleteUser() {
        return "DELETE MAPPING USER";
    }


}
