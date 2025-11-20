package com.munaf.A30_SPRING_AI_2.configs;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean(name = "ollamaChatClient")
    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel) {
        return ChatClient.builder(ollamaChatModel).build();
    }

    @Bean(name = "vertexChatClient")
    public ChatClient vertexChatClient(VertexAiGeminiChatModel vertexChatModel) {
        return ChatClient.builder(vertexChatModel).build();
    }

}
