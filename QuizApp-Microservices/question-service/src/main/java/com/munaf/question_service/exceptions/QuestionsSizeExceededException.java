package com.munaf.question_service.exceptions;

public class QuestionsSizeExceededException extends RuntimeException {
    public QuestionsSizeExceededException(String message) {
        super(message);
    }
}
