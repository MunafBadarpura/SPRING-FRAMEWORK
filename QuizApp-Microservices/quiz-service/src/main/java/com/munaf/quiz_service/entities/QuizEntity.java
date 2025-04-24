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


    @ManyToMany
    @JoinTable(
            name = "question_quiz_table",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<QuestionEntity> questions = new ArrayList<>();

}
