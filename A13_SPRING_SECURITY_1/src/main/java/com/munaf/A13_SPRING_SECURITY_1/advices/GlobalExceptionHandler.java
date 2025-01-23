package com.munaf.A13_SPRING_SECURITY_1.advices;

import com.munaf.A13_SPRING_SECURITY_1.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handlerResourceNotFoundException(ResourceNotFoundException exception){
        ApiError apiError = new ApiError(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError,  HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handlerExceptions(Exception exception){
        ApiError apiError = new ApiError(exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiError,  HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
