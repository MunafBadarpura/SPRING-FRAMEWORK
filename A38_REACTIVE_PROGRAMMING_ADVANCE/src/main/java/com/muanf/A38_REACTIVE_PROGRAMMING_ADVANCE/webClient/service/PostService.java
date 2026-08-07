package com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.webClient.service;

import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.exception.ResourceNotFoundException;
import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.webClient.dto.PostDto;
import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.webClient.dto.PostsResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import javax.naming.ServiceUnavailableException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostService {

    WebClient postWebClient;

    public Flux<PostDto> getAllPosts() {
        return postWebClient.get()
                .retrieve()
                .bodyToMono(PostsResponse.class)
                .flatMapMany(response -> Flux.fromIterable(response.posts()));
    }


    public Mono<PostDto> getPostById(Integer id) {
        return postWebClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        response -> response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(
                                        new ResourceNotFoundException("Post not found with id: " + id + ", response: " + body)
                                ))
                )
                .onStatus( // now this is not needed because we have handled is5xxServerError in WebClientConfig
                        HttpStatusCode::is5xxServerError,
                        response -> Mono.error(new RuntimeException("Server error while fetching post with id : " + id))
                )
                .bodyToMono(PostDto.class)
                .timeout(Duration.ofMillis(2000)) // Duration.ofMillis(1) : to simulate timeout
                .retryWhen(
                        Retry.backoff(3, Duration.ofMillis(500))
                                .filter(throwable -> throwable instanceof ServiceUnavailableException) // instanceof ResourceNotFoundException for simulation
                                .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) ->
                                        new RuntimeException("Retry exhausted for fetching post")
                                ))
                )
                .onErrorMap(TimeoutException.class, ex -> new ServiceUnavailableException("Timeout while fetching post with id : " + id));
    }

}
