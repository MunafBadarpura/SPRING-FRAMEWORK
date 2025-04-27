package com.munaf.quizApp.services;

import com.munaf.quizApp.entities.QuestionEntity;
import com.munaf.quizApp.entities.QuizEntity;
import com.munaf.quizApp.exceptions.QuestionsSizeExceededException;
import com.munaf.quizApp.exceptions.ResourceNotFoundException;
import com.munaf.quizApp.models.QuestionWrapper;
import com.munaf.quizApp.models.QuizResponse;
import com.munaf.quizApp.models.ResponseModel;
import com.munaf.quizApp.repositories.QuestionsRepository;
import com.munaf.quizApp.repositories.QuizRepository;
import com.munaf.quizApp.utils.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.naming.LimitExceededException;
import java.util.List;
import java.util.Objects;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionsRepository questionsRepository;

    public QuizService(QuizRepository quizRepository, QuestionsRepository questionsRepository) {
        this.quizRepository = quizRepository;
        this.questionsRepository = questionsRepository;
    }

    private void checkSizeForCategory(String category, Integer size) {
        List<QuestionEntity> questionEntities = questionsRepository.findByCategory(category);
        if (size > questionEntities.size()) {
            throw new QuestionsSizeExceededException(size + " questions not available for category : " + category);
        }
    }

    public ResponseModel createQuiz(String category, Integer size, String title) {
        checkSizeForCategory(category, size);
        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setQuizTitle(title);
        quizEntity.setQuestionsSize(size);
        quizEntity.setQuizCategory(category);
        quizEntity.setQuestions(questionsRepository.getRandomQuestionsByCategoryWithSize(category, size));

        QuizEntity savedQuizEntity = quizRepository.save(quizEntity);

        return CommonResponse.createResponse(savedQuizEntity, HttpStatus.CREATED);
    }


    public ResponseModel getQuizById(Long quizId) {
        QuizEntity quizEntity = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz Not Found With Id : " + quizId));

        List<QuestionEntity> questionEntities = quizEntity.getQuestions();

        List<QuestionWrapper> questionWrapperList = questionEntities.stream()
                .map(questionEntity -> QuestionWrapper.convertToQuestionWrapper(questionEntity))
                .toList();

        return CommonResponse.createResponse(questionWrapperList, HttpStatus.OK);
    }

    public ResponseModel calculateQuizScore(Long quizId, List<QuizResponse> quizResponses) {
        QuizEntity quizEntity = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz Not Found With Id : " + quizId));

        List<QuestionEntity> questionEntities = quizEntity.getQuestions();
        int totalScore = questionEntities.size();
        int score = 0;


        for (QuestionEntity questions : questionEntities) {
            for (QuizResponse response : quizResponses) {
                if (questions.getId() == response.getId() &&
                    questions.getAnswer().equalsIgnoreCase(response.getAnswer()))
                {
                    score++;
                }
            }
        }

        return CommonResponse.createResponse("SCORE = " + score + " / OUT OF " + totalScore, HttpStatus.OK);
    }

}






