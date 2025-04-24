package com.munaf.question_service.models;

import com.munaf.question_service.entities.QuestionEntity;
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


    public static QuestionWrapper convertToQuestionWrapper(QuestionEntity questionEntity) {
        QuestionWrapper questionWrapper = new QuestionWrapper();
        questionWrapper.setId(questionEntity.getId());
        questionWrapper.setTitle(questionEntity.getTitle());
        questionWrapper.setCategory(questionEntity.getCategory());
        questionWrapper.setDifficultyLevel(questionEntity.getDifficultyLevel());
        questionWrapper.setOption1(questionEntity.getOption1());
        questionWrapper.setOption2(questionEntity.getOption2());
        questionWrapper.setOption3(questionEntity.getOption3());
        questionWrapper.setOption4(questionEntity.getOption4());


        return questionWrapper;
    }

}
