package com.munaf.SpringBoot4Demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiConfigForQueryVersioning implements WebMvcConfigurer {

//    @Override
//    public void configureApiVersioning(ApiVersionConfigurer configurer) {
//
//        // Version will come from query parameter
//        // Example: /hello?v=1
//        configurer.useQueryParam("v"); // or "api-version"
//    }

//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//
//        // No version in path → only add base API prefix
//        configurer.addPathPrefix("/api",
//                HandlerTypePredicate.forAnnotation(RestController.class));
//    }
}
