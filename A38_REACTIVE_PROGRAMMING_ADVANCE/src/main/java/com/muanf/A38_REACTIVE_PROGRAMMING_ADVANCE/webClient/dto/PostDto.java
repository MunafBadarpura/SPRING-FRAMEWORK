package com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.webClient.dto;

import java.util.List;

public record PostDto(
        Integer id,
        String title,
        String body,
        List<String> tags
) {
}