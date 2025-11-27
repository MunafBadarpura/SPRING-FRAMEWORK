package com.munaf.A30_SPRING_AI_2.configs;

import com.munaf.A30_SPRING_AI_2.advisors.TokenLogAdvisors;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Autowired
    private JdbcChatMemoryRepository chatMemoryRepository;


    @Bean(name = "vertexChatClient")
    public ChatClient vertexChatClient(VertexAiGeminiChatModel vertexChatModel) {

        // creating chatMemory
        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(chatMemoryRepository)
                .maxMessages(20) // default is 20 , maxMessages = userMessage + llmMessage
                .build();

        // creating MessageChatMemoryAdvisor
        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor
                .builder(chatMemory)
                .build();

        return ChatClient
                .builder(vertexChatModel)
                .defaultAdvisors(messageChatMemoryAdvisor, new TokenLogAdvisors(), new SafeGuardAdvisor(List.of("Hack", "Suicide")))
                .build();
    }


    @Bean(name = "ollamaChatClient")
    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel) {
        return ChatClient
                .builder(ollamaChatModel)
                .build();
    }

}
