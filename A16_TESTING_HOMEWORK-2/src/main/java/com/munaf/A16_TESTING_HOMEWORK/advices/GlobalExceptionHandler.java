package com.munaf.A16_TESTING_HOMEWORK.advices;

import com.munaf.A16_TESTING_HOMEWORK.exceptions.InvalidInputException;
import com.munaf.A16_TESTING_HOMEWORK.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError, HttpStatus status) {
        return new ResponseEntity<>(new ApiResponse<>(apiError,status), status);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(ResourceNotFoundException e) {
        ApiError apiError = ApiError
                .builder()
                .message(e.getMessage())
                .build();

        return buildErrorResponseEntity(apiError,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidInputException(InvalidInputException e) {
        ApiError apiError = ApiError
                .builder()
                .message(e.getMessage())
                .build();

        return buildErrorResponseEntity(apiError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        ApiError apiError = ApiError
                .builder()
                .message("Input Validation Error")
                .subErrors(errors)
                .build();

        return buildErrorResponseEntity(apiError,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        ApiError apiError = ApiError
                .builder()
                .message(e.getMessage())
                .build();

        return buildErrorResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
