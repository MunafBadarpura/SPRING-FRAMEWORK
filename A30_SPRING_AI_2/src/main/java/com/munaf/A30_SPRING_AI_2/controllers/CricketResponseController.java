package com.munaf.A30_SPRING_AI_2.controllers;

import com.munaf.A30_SPRING_AI_2.dto.CricketResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cricket")
public class CricketResponseController {

    private ChatClient vertexChatClient;

    public CricketResponseController(@Qualifier("vertexChatClient") ChatClient vertexChatClient) {
        this.vertexChatClient = vertexChatClient;
    }

    @GetMapping("/response/{prompt}")
    public CricketResponse cricketResponse(@PathVariable String prompt) {
        // Who is the highest run scorer in cricket?
        CricketResponse cricketResponse = vertexChatClient
                .prompt(prompt)
                .call()
                .entity(CricketResponse.class);

        return cricketResponse;
    }

    @GetMapping("/multiple/response/{prompt}")
    public List<CricketResponse> cricketMultiPleResponse(@PathVariable String prompt) {
        // give me top 10 cricket players
        List<CricketResponse> cricketResponseList = vertexChatClient
                .prompt(prompt)
                .call()
                .entity(new ParameterizedTypeReference<List<CricketResponse>>() {});

        return cricketResponseList;
    }

}
