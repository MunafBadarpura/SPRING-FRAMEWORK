package com.munaf.question_service.controllers;


import com.munaf.question_service.entities.QuestionEntity;
import com.munaf.question_service.models.QuizResponse;
import com.munaf.question_service.models.ResponseModel;
import com.munaf.question_service.services.QuestionsService;
import com.munaf.question_service.utils.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionsController {

    private final QuestionsService questionsService;

    public QuestionsController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @GetMapping("/ping")
    public ResponseEntity<ResponseModel> getPing() {
        return new ResponseEntity<>(CommonResponse.createResponse("PONG", HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseModel> getAllQuestions() {
        return new ResponseEntity<>(questionsService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ResponseModel> getByCategory(@PathVariable String category) {
        return new ResponseEntity<>(questionsService.getByCategory(category), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseModel> createQuestion(@RequestBody QuestionEntity questionEntity) {
        return new ResponseEntity<>(questionsService.createQuestion(questionEntity), HttpStatus.CREATED);
    }

    //----------

    @GetMapping("/generate")
    public ResponseEntity<ResponseModel> getQuestionsForQuiz(@RequestParam String category,
                                                             @RequestParam Integer size
                                                             ) {
        return new ResponseEntity<>(questionsService.getQuestionsForQuiz(category, size), HttpStatus.OK);
    }

    @PostMapping("/get-questions")
    public ResponseEntity<ResponseModel> getQuestionsFromId(@RequestBody List<Long> questionsIds) {
        return new ResponseEntity<>(questionsService.getQuestionsFromId(questionsIds), HttpStatus.OK);
    }

    @PostMapping("/get-score")
    public ResponseEntity<ResponseModel> getScore(@RequestBody List<QuizResponse> quizResponses) {
        return new ResponseEntity<>(questionsService.getScore(quizResponses), HttpStatus.OK);
    }

}
