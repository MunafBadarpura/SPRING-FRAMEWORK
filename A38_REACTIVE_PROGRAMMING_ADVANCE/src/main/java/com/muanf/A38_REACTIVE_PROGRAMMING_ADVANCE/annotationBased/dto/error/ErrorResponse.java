package com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.dto.error;

import lombok.Builder;

@Builder
public record ErrorResponse (
        String message,
        String code,
        String timestamp
){}
