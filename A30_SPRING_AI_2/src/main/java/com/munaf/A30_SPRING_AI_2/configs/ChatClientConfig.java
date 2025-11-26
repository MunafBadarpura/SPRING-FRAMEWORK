package com.munaf.A30_SPRING_AI_2.configs;

import com.munaf.A30_SPRING_AI_2.advisors.TokenLogAdvisors;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean(name = "ollamaChatClient")
    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel) {
        return ChatClient
                .builder(ollamaChatModel)
                .build();
    }

    @Bean(name = "vertexChatClient")
    public ChatClient vertexChatClient(VertexAiGeminiChatModel vertexChatModel) {
        return ChatClient
                .builder(vertexChatModel)
                .defaultAdvisors(new TokenLogAdvisors(), new SafeGuardAdvisor(List.of("Hack", "Suicide")))
                .build();
    }

}
