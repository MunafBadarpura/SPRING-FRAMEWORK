package com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.webClient.dto;

public record UserDto(
        Integer id,
        String firstName,
        String lastName,
        String username,
        String email,
        String phone,
        Integer age,
        String gender,
        String image
) {
}