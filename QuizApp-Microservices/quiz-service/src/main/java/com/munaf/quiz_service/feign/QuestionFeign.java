package com.munaf.quiz_service.feign;

import com.munaf.quiz_service.models.QuizResponse;
import com.munaf.quiz_service.models.ResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizFeign {

    @GetMapping("/generate")
    public ResponseEntity<ResponseModel> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer size);

    @PostMapping("/get-questions")
    public ResponseEntity<ResponseModel> getQuestionsFromId(@RequestBody List<Long> questionsIds);

    @PostMapping("/get-score")
    public ResponseEntity<ResponseModel> getScore(@RequestBody List<QuizResponse> quizResponses);

}
