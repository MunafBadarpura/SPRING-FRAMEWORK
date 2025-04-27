package com.munaf.question_service.services;

import com.munaf.question_service.entities.QuestionEntity;
import com.munaf.question_service.exceptions.ResourceNotFoundException;
import com.munaf.question_service.models.QuestionWrapper;
import com.munaf.question_service.models.QuizResponse;
import com.munaf.question_service.models.ResponseModel;
import com.munaf.question_service.repositories.QuestionsRepository;
import com.munaf.question_service.utils.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    //--------

    public ResponseModel getQuestionsForQuiz(String category, Integer size) {
        List<Long> questionsIds = questionsRepository.getRandomQuestionsByCategoryWithSize(category, size);
        return CommonResponse.createResponse(questionsIds, HttpStatus.OK);
    }


    public ResponseModel getQuestionsFromId(List<Long> questionsIds) {
        List<QuestionEntity> questionEntities = new ArrayList<>();
        for (Long questionsId: questionsIds) {
            questionEntities.add(questionsRepository.findById(questionsId).get());
        }

        List<QuestionWrapper> questionWrapperList = new ArrayList<>();

        for (QuestionEntity questionEntity : questionEntities) {
            QuestionWrapper questionWrapper = new QuestionWrapper();
            questionWrapper.setId(questionEntity.getId());
            questionWrapper.setCategory(questionEntity.getCategory());
            questionWrapper.setTitle(questionEntity.getTitle());
            questionWrapper.setDifficultyLevel(questionEntity.getDifficultyLevel());
            questionWrapper.setOption1(questionEntity.getOption1());
            questionWrapper.setOption2(questionEntity.getOption2());
            questionWrapper.setOption3(questionEntity.getOption3());
            questionWrapper.setOption4(questionEntity.getOption4());

            questionWrapperList.add(questionWrapper);
        }

        return CommonResponse.createResponse(questionWrapperList, HttpStatus.OK);
    }

    public ResponseModel getScore(List<QuizResponse> quizResponses) {
        int score = 0;
            for (QuizResponse quizResponse : quizResponses) {
            QuestionEntity questionEntity = questionsRepository.findById(quizResponse.getId()).
                    orElseThrow(() -> new ResourceNotFoundException("Question Not Found With Id :" + quizResponse.getId()));

            if (quizResponse.getAnswer().equalsIgnoreCase(questionEntity.getAnswer())) score++;
        }

        return CommonResponse.createResponse("SCORE = " + score, HttpStatus.OK);
    }
}







