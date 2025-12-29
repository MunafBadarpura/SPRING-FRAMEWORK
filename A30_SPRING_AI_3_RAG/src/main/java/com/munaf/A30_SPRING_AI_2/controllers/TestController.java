package com.munaf.A30_SPRING_AI_2.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat/test")
@RequiredArgsConstructor
public class TestController {

    private final ChatClient vertexChatClient;
    private final EmbeddingModel embeddingModel;

    @GetMapping("/{word}")
    public float[] getEmbeddings(@PathVariable String word) {
        return embeddingModel.embed(word);
    }


}
