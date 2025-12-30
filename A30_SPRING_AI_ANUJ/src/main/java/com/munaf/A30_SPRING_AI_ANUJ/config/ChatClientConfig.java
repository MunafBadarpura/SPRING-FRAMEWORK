package com.munaf.A30_SPRING_AI_ANUJ.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Autowired
    private JdbcChatMemoryRepository chatMemoryRepository;

    @Bean
    @Qualifier("vertexChatClient")
    public ChatClient vertexChatClient(VertexAiGeminiChatModel chatModel) {
        return ChatClient
                .builder(chatModel)
                .build();
    }


    // chat memory bean (because by default it is in memory)
    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(chatMemoryRepository)
                .maxMessages(10)
                .build();
    }

}
