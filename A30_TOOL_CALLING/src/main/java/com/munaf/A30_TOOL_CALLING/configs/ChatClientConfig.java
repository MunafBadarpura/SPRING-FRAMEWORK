package com.munaf.A30_TOOL_CALLING.configs;

import com.munaf.A30_TOOL_CALLING.tools.DateTimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    @Qualifier("vertexChatClint")
    public ChatClient vertexChatClint(VertexAiGeminiChatModel vertexChatModel) {
        return ChatClient
                .builder(vertexChatModel)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

}
