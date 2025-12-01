package com.munaf.SpringBoot4Demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiConfigForPathVersioning implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {

        // Tell Spring: version will come from the URL path segment
        // Example: /api/v1/hello → version = 1
        configurer.usePathSegment(1); // Path
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

        // Add a prefix for ALL RestControllers
        // It dynamically turns /api/v{version} into:
        // /api/v1, /api/v2, /api/v3 ...
        configurer.addPathPrefix("/api/v{version}",
                HandlerTypePredicate.forAnnotation(RestController.class));
    }

}
