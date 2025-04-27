package com.munaf.quizApp.controllers;

import com.munaf.quizApp.entities.QuestionEntity;
import com.munaf.quizApp.models.ResponseModel;
import com.munaf.quizApp.services.QuestionsService;
import com.munaf.quizApp.utils.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
