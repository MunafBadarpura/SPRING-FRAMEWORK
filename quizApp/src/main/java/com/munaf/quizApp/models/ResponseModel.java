package com.munaf.quizApp.entities;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseModel {

    private HttpStatus status;
    private Integer statusCode;
    private Object data;

    public ResponseModel(HttpStatus status, Object data) {
        this.status = status;
        this.statusCode = status.value();
        this.data = data;
    }
}
