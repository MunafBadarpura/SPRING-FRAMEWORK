package com.munaf.quiz_service.services;


import com.munaf.quiz_service.entities.QuizEntity;

import com.munaf.quiz_service.exceptions.ResourceNotFoundException;
import com.munaf.quiz_service.feign.QuestionFeign;
import com.munaf.quiz_service.models.QuestionWrapper;
import com.munaf.quiz_service.models.QuizResponse;
import com.munaf.quiz_service.models.ResponseModel;
import com.munaf.quiz_service.repositories.QuizRepository;
import com.munaf.quiz_service.utils.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionFeign questionFeign;

    public QuizService(QuizRepository quizRepository, QuestionFeign questionFeign) {
        this.quizRepository = quizRepository;
        this.questionFeign = questionFeign;
    }

//    private void checkSizeForCategory(String category, Integer size) {
//        List<QuestionEntity> questionEntities = questionsRepository.findByCategory(category);
//        if (size > questionEntities.size()) {
//            throw new QuestionsSizeExceededException(size + " questions not available for category : " + category);
//        }
//    }

    public ResponseModel createQuiz(String category, Integer size, String title) {

        List<Integer> questions = (List<Integer>) questionFeign.getQuestionsForQuiz(category, size).getBody().getData();

        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setQuizTitle(title);
        quizEntity.setQuestionsSize(size);
        quizEntity.setQuizCategory(category);
        quizEntity.setQuestionsIds(questions);

        QuizEntity savedQuizEntity = quizRepository.save(quizEntity);

        return CommonResponse.createResponse(savedQuizEntity, HttpStatus.CREATED);
    }


    public ResponseModel getQuizById(Long quizId) {
        QuizEntity quizEntity = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz Not Found With Id : " + quizId));

        List<Integer> questionsIds = quizEntity.getQuestionsIds();
        List<QuestionWrapper> questionWrapperList = (List<QuestionWrapper>) questionFeign.getQuestionsFromId(questionsIds).getBody().getData();

        return CommonResponse.createResponse(questionWrapperList, HttpStatus.OK);
    }

    public ResponseModel calculateQuizScore(Long quizId, List<QuizResponse> quizResponses) {

        String scoreResult = (String) questionFeign.getScore(quizResponses).getBody().getData();
        return CommonResponse.createResponse(scoreResult, HttpStatus.OK);
//        QuizEntity quizEntity = quizRepository.findById(quizId)
//                .orElseThrow(() -> new ResourceNotFoundException("Quiz Not Found With Id : " + quizId));
//
//        List<QuestionEntity> questionEntities = quizEntity.getQuestionsIds();
//        int totalScore = questionEntities.size();
//        int score = 0;
//
//
//        for (QuestionEntity questions : questionEntities) {
//            for (QuizResponse response : quizResponses) {
//                if (questions.getId() == response.getId() &&
//                    questions.getAnswer().equalsIgnoreCase(response.getAnswer()))
//                {
//                    score++;
//                }
//            }
//        }
//
//        return CommonResponse.createResponse("SCORE = " + score + " / OUT OF " + totalScore, HttpStatus.OK);
    }

}






