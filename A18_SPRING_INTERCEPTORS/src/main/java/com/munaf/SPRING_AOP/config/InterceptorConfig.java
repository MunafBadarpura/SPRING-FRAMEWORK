package com.munaf.SPRING_AOP.config;

import com.munaf.SPRING_AOP.interceptor.ProductInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
This is the configuration file for to configure interceptor in our application
Here we can define interceptor class that we have created
also define in which route we need to add this interceptor
and also define in which routes you dont need to define this interceptors
* */


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ProductInterceptor())
                .addPathPatterns("/products/**")
                .excludePathPatterns("/products/delete");
    }
}
