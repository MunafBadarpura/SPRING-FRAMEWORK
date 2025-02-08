package com.munaf.SPRING_SECURITY_PRAC.restClient.services;

import com.munaf.SPRING_SECURITY_PRAC.restClient.dtos.TodoDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;

/*

==>How Exceptions Are Handled by Default
If the server returns 2xx (Success) → The response body is returned.
If the server returns 4xx (Client error, e.g., 404, 400) → HttpClientErrorException is thrown.
If the server returns 5xx (Server error, e.g., 500, 503) → HttpServerErrorException is thrown.
If there's a network issue → RestClientException is thrown.\

===> To gracefully handle errors, you should use onStatus(...) or try-catch blocks.
* */


@Service
public class RestService {

    private final RestClient todosRestClient;

    public RestService(RestClient todosRestClient) {
        this.todosRestClient = todosRestClient;
    }


    public List<TodoDTO> getAllTodos() {
        return todosRestClient
                .get()
                .uri("/todos")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req,res) -> {
                    throw new HttpClientErrorException(res.getStatusCode(), "Client error occurred");
                })
                .onStatus(HttpStatusCode::is5xxServerError, (req,res) -> {
                    throw new RestClientException("Server error occurred");
                })
                .body(new ParameterizedTypeReference<List<TodoDTO>>() {});
    }


    public TodoDTO getTodoById(Long todoId) {
        return todosRestClient
                .get()
                .uri("/todos/{todoId}", todoId)
                .retrieve()
                .body(TodoDTO.class);
    }

    public TodoDTO createNewTodo(TodoDTO todoDTO) {
        return todosRestClient
                .post()
                .uri("/todos")
                .body(todoDTO)
                .retrieve()
                .body(TodoDTO.class);
    }

    public TodoDTO deleteTodo(Long todoId) {
        return todosRestClient
                .delete()
                .uri("/todos/{todoId}", todoId)
                .retrieve()
                .body(TodoDTO.class);
    }



}
