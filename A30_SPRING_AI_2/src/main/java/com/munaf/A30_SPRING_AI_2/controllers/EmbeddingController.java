package com.munaf.A30_SPRING_AI_2.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat/embedding")
@RequiredArgsConstructor
public class EmbeddingController {

    private final ChatClient vertexChatClient;
    private final EmbeddingModel embeddingModel;

    @GetMapping("/{word}")
    public float[] embedding(@PathVariable String word) {
        return embeddingModel.embed(word);
    }


    @GetMapping("/test/{language}")
    public String getResponse(@PathVariable String language) {
        String prompt = "give me simple code for {language}";

        return vertexChatClient.prompt()
                .user(u -> u.text(prompt).param("language", language))
                .call()
                .content();

    }


}
