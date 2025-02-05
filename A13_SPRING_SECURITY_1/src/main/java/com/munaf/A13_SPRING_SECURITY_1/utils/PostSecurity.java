package com.munaf.A13_SPRING_SECURITY_1.utils;

import com.munaf.A13_SPRING_SECURITY_1.dto.PostDTO;
import com.munaf.A13_SPRING_SECURITY_1.entity.PostEntity;
import com.munaf.A13_SPRING_SECURITY_1.entity.User;
import com.munaf.A13_SPRING_SECURITY_1.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostSecurity {

    private final PostService postService;

    public boolean isAuthorOfPost(Long postId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostDTO postDTO = postService.getPostById(postId);

        Long userId = user.getId();
        Long authorId = postDTO.getAuthor().getId();

        return userId.equals(authorId);
    }

}









