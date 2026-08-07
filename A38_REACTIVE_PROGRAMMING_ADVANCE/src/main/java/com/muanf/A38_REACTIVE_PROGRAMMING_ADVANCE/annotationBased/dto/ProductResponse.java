package com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProductResponse(
        String id,
        String name,
        Double price,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
