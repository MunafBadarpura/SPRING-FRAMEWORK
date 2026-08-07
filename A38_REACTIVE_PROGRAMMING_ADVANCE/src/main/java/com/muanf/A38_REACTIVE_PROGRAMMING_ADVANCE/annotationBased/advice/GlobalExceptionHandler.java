package com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.advice;

import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.dto.error.ErrorResponse;
import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.exception.ResourceAlreadyExistsException;
import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.exception.ResourceNotFoundException;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import javax.naming.ServiceUnavailableException;
import java.util.Date;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public Mono<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return Mono.just(
                ErrorResponse.builder()
                        .message(ex.getMessage())
                        .code(HttpStatus.NOT_FOUND.name())
                        .timestamp(new Date().toString())
                        .build()
        );
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public Mono<ErrorResponse> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        return Mono.just(
                ErrorResponse.builder()
                        .message(ex.getMessage())
                        .code(HttpStatus.CONFLICT.name())
                        .timestamp(new Date().toString())
                        .build()
        );
    }

    @ExceptionHandler(ValidationException.class)
    public Mono<ErrorResponse> handleValidationException(ValidationException ex) {
        return Mono.just(
                ErrorResponse.builder()
                        .message(ex.getMessage())
                        .code(HttpStatus.BAD_REQUEST.name())
                        .timestamp(new Date().toString())
                        .build()
        );
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public Mono<ErrorResponse> handleServiceUnavailableException(ServiceUnavailableException ex) {
        return Mono.just(
                ErrorResponse.builder()
                        .message(ex.getMessage())
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.name())
                        .timestamp(new Date().toString())
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public Mono<ErrorResponse> handleGenericException(Exception ex) {
        return Mono.just(
                ErrorResponse.builder()
                        .message(ex.getMessage())
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.name())
                        .timestamp(new Date().toString())
                        .build()
        );
    }


}
