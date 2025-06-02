package com.munaf.SPRING_SECURITY_PRAC.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "HOME PAGE";
    }

    @GetMapping("/success")
    public String success(@RequestParam(required = false) String token) {
        return "Successfully Logged In";
    }

    @GetMapping("/post")
    @PreAuthorize("hasAnyRole('USER, ADMIN')") // we not pass ROLE_ prefix in hasRole & hasAnyRole
    public String getPost() {
        return "GET MAPPING POST";
    }

    @GetMapping("post/{postId}")
    @PreAuthorize("@postSecurity.isOwnerOfPost(#postId)") // if id is even it return true
    public String getPostById(@PathVariable Long postId) {
        return "POST WITH ID" + postId;
    }


    @Secured({ "ROLE_CREATOR", "ROLE_ADMIN" })
    @PreAuthorize("hasAnyAuthority('POST_CREATE, POST_VIEW')")
    @PostMapping("/post")
    public String postPost() {
        return "POST MAPPING POST";
    }

    @PutMapping("/post")
    public String putPost() {
        return "PUT MAPPING POST";
    }

    @DeleteMapping("/post")
    @PreAuthorize("hasRole('ROLE_ADMIN') AND hasAuthority('POST_DELETE')")
    public String deletePost() {
        return "DELETE MAPPING POST";
    }


    @GetMapping("/user")
    public String getUserMapping() {
        return "GET MAPPING USER";
    }

    @PostMapping("/user")
    public String postUser() {
        return "POST MAPPING USER";
    }

    @PutMapping("/user")
    public String putUser() {
        return "PUT MAPPING USER";
    }

    @DeleteMapping("/user")
    public String deleteUser() {
        return "DELETE MAPPING USER";
    }


}
