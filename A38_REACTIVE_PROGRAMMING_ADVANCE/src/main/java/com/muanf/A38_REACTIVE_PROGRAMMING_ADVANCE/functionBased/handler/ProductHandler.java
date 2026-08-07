package com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.functionBased.handler;

import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.dto.ProductCreateRequest;
import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.exception.ResourceNotFoundException;
import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.service.ProductService;
import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.functionBased.validator.RequestValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductHandler {

    ProductService productService;
    RequestValidator validator;

    public Mono<ServerResponse> addProduct(ServerRequest request) {
        return request.bodyToMono(ProductCreateRequest.class)
                .flatMap(productCreateRequest -> validator.validate(productCreateRequest))
                .flatMap(productCreateRequest -> productService.addProductWithFunctional(productCreateRequest))
                .flatMap(productResponse -> ServerResponse.ok().bodyValue(productResponse));
    }

    public Mono<ServerResponse> getProductById(ServerRequest request) {
        return productService.getProductById(request.pathVariable("id"))
                .flatMap(productResponse -> ServerResponse.ok().bodyValue(productResponse))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Product not found with id : " + request.pathVariable("id"))));
    }

    public Mono<ServerResponse> getAllProducts(ServerRequest request) {
        return productService.getAllProducts(0, 10)
                .flatMap(productResponse -> ServerResponse.ok().bodyValue(productResponse));
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest request) {
        return productService.deleteProduct(request.pathVariable("id"))
                .flatMap(productResponse -> ServerResponse.ok().bodyValue(productResponse))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Product not found with id : " + request.pathVariable("id"))));
    }




}
