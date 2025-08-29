package com.munaf.A30_SPRING_AI_01.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeminiResponseDTO {


    private String response;

    private String id;

    private String modelName;

    private Integer promptTokens;

    private Integer completionTokens;



}
