package com.munaf.user_service.controllers;

import com.munaf.user_service.entity.UserEntity;
import com.munaf.user_service.services.UserService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public String createUser(@RequestBody UserEntity userEntity) {
        return userService.createUser(userEntity);
    }

}
