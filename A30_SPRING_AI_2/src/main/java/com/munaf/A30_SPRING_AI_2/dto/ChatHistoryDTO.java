package com.munaf.A30_SPRING_AI_2.dto;

import com.munaf.A30_SPRING_AI_2.entities.ChatHistory;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatHistoryDTO {

    private String chatHistoryId;
    private String chatHistoryName;

    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static ChatHistoryDTO mapToDto(ChatHistory chatHistory) {
        ChatHistoryDTO chatHistoryDTO = new ChatHistoryDTO();
        chatHistoryDTO.setChatHistoryId(chatHistory.getChatHistoryId());
        chatHistoryDTO.setChatHistoryName(chatHistory.getChatHistoryName());
        chatHistoryDTO.setUserId(chatHistory.getUserId());
        chatHistoryDTO.setCreatedAt(chatHistory.getCreatedAt());
        chatHistoryDTO.setUpdatedAt(chatHistory.getUpdatedAt());
        return chatHistoryDTO;
    }

}
