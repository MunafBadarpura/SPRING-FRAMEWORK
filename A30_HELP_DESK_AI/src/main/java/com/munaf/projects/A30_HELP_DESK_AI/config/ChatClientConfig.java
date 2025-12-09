package com.munaf.projects.A30_HELP_DESK_AI.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
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
        // creating chatMemory
        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(chatMemoryRepository)
                .maxMessages(20) // default is 20 , maxMessages = userMessage + llmMessage
                .build();

        // creating MessageChatMemoryAdvisor
        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor
                .builder(chatMemory)
                .build();

        return ChatClient.builder(chatModel)
                .defaultAdvisors(messageChatMemoryAdvisor)
                .build();
    }

}
