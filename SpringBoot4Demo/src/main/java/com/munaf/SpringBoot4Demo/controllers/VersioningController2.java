package com.munaf.SpringBoot4Demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "/test2", version = "1") // all apis in api/v1
public class VersioningController2 {


    // problem detail example
    @GetMapping()
    public ProblemDetail problemDetail() {
        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND, "User not found");

        problemDetail.setTitle("User Not Found");
        problemDetail.setType(URI.create("https://example.com/errors/not-found"));
        problemDetail.setProperty("userId", 15); // custom field

        return problemDetail;
    }


}
