package com.munaf.A20_REDIS_HOMEWORK.controllers;

import com.munaf.A20_REDIS_HOMEWORK.entities.Account;
import com.munaf.A20_REDIS_HOMEWORK.entities.User;
import com.munaf.A20_REDIS_HOMEWORK.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;


    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userService.signup(user);
    }

}
