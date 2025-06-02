package com.munaf.SPRING_SECURITY_PRAC.utils;

import org.springframework.stereotype.Component;

@Component
public class PostSecurity {

    public boolean isOwnerOfPost(Long postId) {
        return postId % 2 == 0;
    }

}
