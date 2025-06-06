package com.munaf.A12_PROD_READY_FEATURE.advices;

import com.munaf.A12_PROD_READY_FEATURE.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ApiResponse<?> convertToApiResponse(ApiError error){
        return new ApiResponse<>(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handlerResourceNotFoundException(ResourceNotFoundException exception){
        ApiError apiError = new ApiError(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(convertToApiResponse(apiError),  HttpStatus.NOT_FOUND);
    }

}
