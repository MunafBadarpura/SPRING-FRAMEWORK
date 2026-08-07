package com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.exception;

public class ResourceAlreadyExistsException extends  RuntimeException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
