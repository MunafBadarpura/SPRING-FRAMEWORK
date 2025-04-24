package com.munaf.question_service.utils;

import com.munaf.question_service.models.ResponseModel;
import org.springframework.http.HttpStatus;

public class CommonResponse {

    public static ResponseModel createResponse(Object data, HttpStatus status) {
        return new ResponseModel(status, data);
    }


}
