package com.munaf.SPRING_SECURITY_PRAC.restClient.controllers;

import com.munaf.SPRING_SECURITY_PRAC.restClient.dtos.TodoDTO;
import com.munaf.SPRING_SECURITY_PRAC.restClient.services.RestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class RestClientController {

    private final RestService restService;

    public RestClientController(RestService restService) {
        this.restService = restService;
    }


    @GetMapping("/{todoId}")
    public TodoDTO getTodoById(@PathVariable Long todoId) {
        return restService.getTodoById(todoId);
    }

    @PostMapping()
    public TodoDTO createNewTodo(@RequestBody TodoDTO todoDTO) {
        return restService.createNewTodo(todoDTO);
    }

    @GetMapping
    public List<TodoDTO> getAllTodos() {
        return restService.getAllTodos();
    }


    @DeleteMapping("/{todoId}")
    public TodoDTO deleteTodo(@PathVariable Long todoId) {
        return restService.deleteTodo(todoId);
    }

}
