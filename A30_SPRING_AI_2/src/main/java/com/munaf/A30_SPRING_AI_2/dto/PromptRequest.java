package com.munaf.A30_SPRING_AI_2.dto;

import lombok.Data;

@Data
public class PromptRequest {

    private String userPrompt;
    private Long userId;
    private String chatHistoryId;
    private String model = "gemini-2.0-flash";

}
