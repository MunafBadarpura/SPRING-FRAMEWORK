package com.munaf.SpringBoot4Demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiConfigForHeaderVersioning implements WebMvcConfigurer {

//    @Override
//    public void configureApiVersioning(ApiVersionConfigurer configurer) {
//
//        // Version will come from request header
//        // Example: API-Version: 1
//        configurer.useRequestHeader("API-Version");
//    }
//
//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//
//        // No path prefix needed because version is NOT in path
//        // Keep URLs clean
//        // /hello
//        configurer.addPathPrefix("/api",
//                HandlerTypePredicate.forAnnotation(RestController.class));
//    }
}
