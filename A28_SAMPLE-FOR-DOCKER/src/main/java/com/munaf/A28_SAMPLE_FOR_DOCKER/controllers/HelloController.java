package com.munaf.A28_SAMPLE_FOR_DOCKER.controllers;

import com.munaf.A28_SAMPLE_FOR_DOCKER.entities.Product;
import com.munaf.A28_SAMPLE_FOR_DOCKER.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private final ProductService productService;

    public HelloController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String hello() {
        return "Hello World";
    }


    @PostMapping("/create")
    public String createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/get-all-products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


}
// A28_SAMPLE-FOR-DOCKER-0.0.1-SNAPSHOT.jar