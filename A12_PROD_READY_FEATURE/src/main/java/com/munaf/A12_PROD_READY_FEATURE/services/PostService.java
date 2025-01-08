package com.munaf.A12_PROD_READY_FEATURE.services;

import com.munaf.A12_PROD_READY_FEATURE.dtos.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO postDTO);

    PostDTO getPostById(Long postId);

    PostDTO updatePost(Long postId, PostDTO postDTO);

    String deletePost(Long postId);
}
