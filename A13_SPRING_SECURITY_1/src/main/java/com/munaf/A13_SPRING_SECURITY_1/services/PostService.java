package com.munaf.A13_SPRING_SECURITY_1.services;


import com.munaf.A13_SPRING_SECURITY_1.dto.PostDTO;
import com.munaf.A13_SPRING_SECURITY_1.entity.PostEntity;
import com.munaf.A13_SPRING_SECURITY_1.entity.User;
import com.munaf.A13_SPRING_SECURITY_1.exceptions.ResourceNotFoundException;
import com.munaf.A13_SPRING_SECURITY_1.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper mapper;

    public PostService(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    public List<PostDTO> getAllPosts() {
        List<PostEntity> posts = postRepository.findAll();
        return posts
                .stream()
                .map(postEntity -> mapper.map(postEntity, PostDTO.class))
                .toList();
    }

    public PostDTO createNewPost(PostDTO postDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostEntity postEntity = mapper.map(postDTO, PostEntity.class);
        postEntity.setAuthor(user);
        return mapper.map(postRepository.save(postEntity), PostDTO.class);
    }

    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post Not Found With ID : " + postId));

        return mapper.map(postEntity, PostDTO.class);
    }

    public PostDTO updatePost(Long postId, PostDTO postDTO) {
        PostEntity postToSave = mapper.map(postDTO, PostEntity.class);
        postToSave.setId(postId);
        PostEntity savedPost = postRepository.save(postToSave);
        return mapper.map(savedPost, PostDTO.class);
    }

    public String deletePost(Long postId) {
        postRepository.deleteById(postId);
        return "Post Deleted";
    }
}
