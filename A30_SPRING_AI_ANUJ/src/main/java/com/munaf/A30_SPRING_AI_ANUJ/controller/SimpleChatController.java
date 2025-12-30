package com.munaf.A30_SPRING_AI_ANUJ.controller;

import com.munaf.A30_SPRING_AI_ANUJ.services.SimpleChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("simple")
@RequiredArgsConstructor
public class SimpleChatController {

    private final SimpleChatService simpleChatService;

    @GetMapping("chat/{userQuery}")
    public String simpleChat(@PathVariable String userQuery) {
        return simpleChatService.simpleChat(userQuery);
    }

}
