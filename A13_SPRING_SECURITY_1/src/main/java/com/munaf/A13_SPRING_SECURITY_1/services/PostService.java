package com.munaf.A13_SPRING_SECURITY_1.services;


import com.munaf.A13_SPRING_SECURITY_1.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO postDTO);

    PostDTO getPostById(Long postId);

    PostDTO updatePost(Long postId, PostDTO postDTO);

    String deletePost(Long postId);
}
