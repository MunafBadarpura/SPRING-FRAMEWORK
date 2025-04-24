package com.munaf.quizApp.exceptions;

public class QuestionsSizeExceededException extends RuntimeException {
  public QuestionsSizeExceededException(String message) {
    super(message);
  }
}
