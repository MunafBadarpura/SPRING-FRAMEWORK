package com.munaf.SPRING_SECURITY_PRAC.restClient.dtos;

import lombok.Data;

@Data
public class TodoDTO {


    private Long userId;
    private Long id;
    private String title;
    private Boolean completed;

}
