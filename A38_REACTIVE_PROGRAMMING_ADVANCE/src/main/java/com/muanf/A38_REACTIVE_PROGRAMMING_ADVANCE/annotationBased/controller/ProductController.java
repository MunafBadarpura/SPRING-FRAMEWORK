package com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.controller;

import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.dto.ProductCreateRequest;
import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.dto.ProductResponse;
import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

//@RestController Todo : Removed this to test function based router
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductController {

    ProductService productService;

    // Add Product
    @PostMapping
    public Mono<ResponseEntity<ProductResponse>> addProduct(@RequestBody @Valid Mono<ProductCreateRequest> requestMono) {
        return productService.addProduct(requestMono)
                .map(product -> ResponseEntity.ok(product));
    }

    // Get product by id
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductResponse>> getProductById(@PathVariable String id) {
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok(product));
    }

    // Get all products
    @GetMapping
    public Mono<ResponseEntity<Page<ProductResponse>>> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return productService.getAllProducts(page, size)
                .map(products -> ResponseEntity.ok(products));
    }

    // Delete product
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id)
                .map(deleted -> ResponseEntity.ok().<Void>build());
    }

}
