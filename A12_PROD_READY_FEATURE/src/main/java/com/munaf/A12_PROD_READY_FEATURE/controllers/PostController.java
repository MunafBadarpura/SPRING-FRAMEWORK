package com.munaf.A12_PROD_READY_FEATURE.controllers;

import com.munaf.A12_PROD_READY_FEATURE.dtos.PostDTO;
import com.munaf.A12_PROD_READY_FEATURE.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("getAllPosts")
    public List<PostDTO> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("{postId}")
    public PostDTO getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping("createNewPost")
    public PostDTO createNewPost(@RequestBody PostDTO postDTO){
        return postService.createNewPost(postDTO);
    }

    @PutMapping("updatePost/{postId}")
    public PostDTO updatePost(@PathVariable Long postId,@RequestBody PostDTO postDTO){
        return postService.updatePost(postId,postDTO);
    }

    @DeleteMapping("deletePost/{postId}")
    public String deletePost(@PathVariable Long postId){
        return postService.deletePost(postId);
    }

}
