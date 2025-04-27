package com.munaf.quizApp.utils;

import com.munaf.quizApp.models.ResponseModel;
import org.springframework.http.HttpStatus;

public class CommonResponse {

    public static ResponseModel createResponse(Object data, HttpStatus status) {
        return new ResponseModel(status, data);
    }


}
