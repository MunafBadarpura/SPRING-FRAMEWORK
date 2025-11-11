package com.munaf.A32_SPRING_BOOT_EVENTS.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    // this exception handler not execute in event listener
    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException ex) {
        log.error("RuntimeException occurred: {}", ex.getMessage());
        return "RuntimeException Error: " + ex.getMessage();
    }


}
