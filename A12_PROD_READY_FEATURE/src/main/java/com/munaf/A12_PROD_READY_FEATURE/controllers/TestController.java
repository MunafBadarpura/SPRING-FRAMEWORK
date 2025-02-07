package com.munaf.A12_PROD_READY_FEATURE.controllers;

import com.munaf.A12_PROD_READY_FEATURE.dtos.TodoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TestController {

    private final RestClient testingRestClient;

    public TestController(RestClient testingRestClient) {
        this.testingRestClient = testingRestClient;
    }

    @GetMapping("/{todoId}")
    public TodoDTO getTodoById(@PathVariable Long todoId) {
        return testingRestClient
                .get()
                .uri("/todos/{id}")
                .retrieve()
                .body(TodoDTO.class);
    }


    @GetMapping
    public List<TodoDTO> getAllTodos() {
        return testingRestClient
                .get()
                .uri("/todos")
                .retrieve()
                .body(new ParameterizedTypeReference<List<TodoDTO>>() {});
    }



}











