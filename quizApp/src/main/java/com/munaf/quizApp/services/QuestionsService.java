package com.munaf.quizApp.services;

import com.munaf.quizApp.entities.QuestionEntity;
import com.munaf.quizApp.models.ResponseModel;
import com.munaf.quizApp.repositories.QuestionsRepository;
import com.munaf.quizApp.utils.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsService {

    private final QuestionsRepository questionsRepository;

    public QuestionsService(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    public ResponseModel getAllQuestions() {
        List<QuestionEntity> questions = questionsRepository.findAll();
        return CommonResponse.createResponse(questions, HttpStatus.OK);
    }

    public ResponseModel getByCategory(String category) {
        List<QuestionEntity> questions = questionsRepository.findByCategory(category);
        return CommonResponse.createResponse(questions, HttpStatus.OK);
    }

    public ResponseModel createQuestion(QuestionEntity questionEntity) {
        QuestionEntity savedQuestion = questionsRepository.save(questionEntity);
        return CommonResponse.createResponse(savedQuestion, HttpStatus.CREATED);
    }
}
