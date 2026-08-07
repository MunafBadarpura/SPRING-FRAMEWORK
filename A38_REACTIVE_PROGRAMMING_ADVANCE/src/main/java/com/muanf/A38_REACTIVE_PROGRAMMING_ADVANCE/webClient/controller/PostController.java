package com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.webClient.controller;

import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.webClient.dto.PostDto;
import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.webClient.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/web-client/posts")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PostController {

    PostService postService;

    @GetMapping
    public ResponseEntity<Flux<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<PostDto>> getPostById(@PathVariable Integer id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }


}
