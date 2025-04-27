package com.munaf.quiz_service.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "quiz_table")
@Data
public class QuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quizTitle;

    private String quizCategory;

    private Integer questionsSize;


    @ElementCollection
    private List<Integer> questionsIds = new ArrayList<>();

}
