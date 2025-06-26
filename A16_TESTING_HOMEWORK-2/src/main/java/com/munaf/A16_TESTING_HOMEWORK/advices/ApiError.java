package com.munaf.A16_TESTING_HOMEWORK.advices;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApiError {

    private String message;
    private List<String> subErrors;

}
