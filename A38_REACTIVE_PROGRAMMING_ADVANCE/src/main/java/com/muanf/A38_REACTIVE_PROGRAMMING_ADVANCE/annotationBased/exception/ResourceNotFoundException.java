package com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.exception;

public class ResourceNotFoundException extends  RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
