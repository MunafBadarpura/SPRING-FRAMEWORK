package com.munaf.quiz_service.models;

import lombok.Data;

@Data
public class QuestionWrapper {

    private Long id;

    private String title;

    private String category;

    private String difficultyLevel;

    private String option1;

    private String option2;

    private String option3;

    private String option4;




}
