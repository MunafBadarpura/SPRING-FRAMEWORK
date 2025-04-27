package com.munaf.quiz_service.feign;

import com.munaf.quiz_service.models.QuizResponse;
import com.munaf.quiz_service.models.ResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "QUESTION-SERVICE")
public interface QuestionFeign {

    @GetMapping("question/generate")
    public ResponseEntity<ResponseModel> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer size);

    @PostMapping("question/get-questions")
    public ResponseEntity<ResponseModel> getQuestionsFromId(@RequestBody List<Integer> questionsIds);

    @PostMapping("question/get-score")
    public ResponseEntity<ResponseModel> getScore(@RequestBody List<QuizResponse> quizResponses);

}
