package com.munaf.A30_SPRING_AI_2.dto;

import com.munaf.A30_SPRING_AI_2.entities.ChatConversation;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatConversationDTO {

    private String chatConversationId;
    private String userContent;
    private String assistantContent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ChatConversationDTO mapToDto(ChatConversation chatConversation) {
        ChatConversationDTO chatConversationDTO = new ChatConversationDTO();
        chatConversationDTO.setChatConversationId(chatConversation.getChatConversationId());
        chatConversationDTO.setUserContent(chatConversation.getUserContent());
        chatConversationDTO.setAssistantContent(chatConversation.getAssistantContent());
        chatConversationDTO.setCreatedAt(chatConversation.getCreatedAt());
        chatConversationDTO.setUpdatedAt(chatConversation.getUpdatedAt());
        return chatConversationDTO;
    }

}
