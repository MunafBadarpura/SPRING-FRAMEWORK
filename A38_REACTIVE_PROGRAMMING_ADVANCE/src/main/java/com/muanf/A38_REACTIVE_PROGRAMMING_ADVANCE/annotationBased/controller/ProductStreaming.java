package com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.controller;

import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.dto.ProductResponse;
import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.entity.Product;
import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/stream")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductStreaming {

    ProductRepository productRepository;

    // Stream all products
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductResponse> streamAllProducts() {
        return productRepository.findAll()
                .map(this::toResponse)
                .delayElements(Duration.ofSeconds(1)); // For testing: simulate live-streaming
    }


    private ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(String.valueOf(product.getId()))
                .name(product.getName())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .build();
    }

}
