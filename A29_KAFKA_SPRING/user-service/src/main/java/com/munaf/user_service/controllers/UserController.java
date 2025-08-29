package com.munaf.user_service.controllers;

import com.munaf.user_service.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/message/{message}")
    public String sendMessage(@PathVariable String message) {
        return userService.sendMessage(message);
    }


}
