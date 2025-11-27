package com.munaf.A30_SPRING_AI_2.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat/stream")
public class StreamResponse {

    private final ChatClient vertexChatClient;

    public StreamResponse(@Qualifier("vertexChatClient") ChatClient vertexChatClient) {
        this.vertexChatClient = vertexChatClient;
    }

    @GetMapping("/response/{prompt}")
    public Flux<String> streamResponse(@PathVariable String prompt) {
        return vertexChatClient
                .prompt()
                .user(userMessage -> userMessage.text(prompt))
                .stream()
                .content();

    }


    @GetMapping(value = "/test")
    public Flux<String> streamResponse2() {

        return vertexChatClient
                .prompt()
                .user("give me list of 10 cricket players")
                .stream()
                .content();
    }


}
