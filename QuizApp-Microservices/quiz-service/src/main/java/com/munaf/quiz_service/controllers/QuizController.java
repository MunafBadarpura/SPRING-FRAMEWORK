package com.munaf.quiz_service.controllers;

import com.munaf.quiz_service.models.QuizResponse;
import com.munaf.quiz_service.models.ResponseModel;
import com.munaf.quiz_service.services.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    @PostMapping("/create")
    public ResponseEntity<ResponseModel> createQuiz(@RequestParam("category") String category,
                                                    @RequestParam("size") Integer size,
                                                    @RequestParam("title") String title
                                                    ) {
        return new ResponseEntity<>(quizService.createQuiz(category, size, title) , HttpStatus.CREATED);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<ResponseModel> getQuizById(@PathVariable Long quizId) {
        return new ResponseEntity<>(quizService.getQuizById(quizId) , HttpStatus.OK);
    }


    @PostMapping("/{quizId}/submit")
    public ResponseEntity<ResponseModel> calculateQuizScore(@PathVariable Long quizId,
                                                            @RequestBody List<QuizResponse> quizResponses
                                                            ) {
        return new ResponseEntity<>(quizService.calculateQuizScore(quizId, quizResponses), HttpStatus.OK);
    }
}
