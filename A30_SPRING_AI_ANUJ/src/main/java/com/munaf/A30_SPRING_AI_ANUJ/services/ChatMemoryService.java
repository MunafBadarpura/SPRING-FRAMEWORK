package com.munaf.A30_SPRING_AI_ANUJ.services;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.VectorStoreChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMemoryService {

    private final ChatClient vertexChatClient;
    private final VectorStore vectorStore;
    private final ChatMemory chatMemory;

    // use short term memory like jdbcChatMemory
    // and then use long term memory like vectorStore chatMemory
    // these will provide best results

    public String chatWithMemory(String userQuery, String userId) {

        // jdbc chatMemory for short memory
        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory)
                .conversationId(userId)
                .build();

        // vectorStore chatMemory for long memory
        VectorStoreChatMemoryAdvisor chatMemoryAdvisor = VectorStoreChatMemoryAdvisor.builder(vectorStore)
                .conversationId(userId)
                .defaultTopK(4) // get first 4 chat messages
                .build();

        return vertexChatClient.prompt()
                .advisors()
                .user(userQuery)
                .advisors(chatMemoryAdvisor)
                .system("Help user with their query")
                .call()
                .content();

    }

}
