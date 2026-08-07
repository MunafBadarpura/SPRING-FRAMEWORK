package com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.functionBased.router;

import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.functionBased.handler.ProductHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class ProductRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(ProductHandler handler) {
        return RouterFunctions.route()
                .POST("/products", request -> handler.addProduct(request))
                .GET("/products/{id}", request -> handler.getProductById(request))
                .GET("/products", handler::getAllProducts) // also can use lambda
                .DELETE("/products/{id}", handler::deleteProduct)
                .build();
    }

}
