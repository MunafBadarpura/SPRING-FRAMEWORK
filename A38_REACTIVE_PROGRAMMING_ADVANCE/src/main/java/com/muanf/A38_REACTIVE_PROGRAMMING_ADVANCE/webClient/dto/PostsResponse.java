package com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.webClient.dto;

import java.util.List;

public record PostsResponse(
        List<PostDto> posts,
        Integer total,
        Integer skip,
        Integer limit
) {
}