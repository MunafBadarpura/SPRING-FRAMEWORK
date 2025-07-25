package com.munaf.A28_SAMPLE_FOR_DOCKER.services;

import com.munaf.A28_SAMPLE_FOR_DOCKER.entities.Product;
import com.munaf.A28_SAMPLE_FOR_DOCKER.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String createProduct(Product product) {
        log.info("Creating product with name : {}" , product.getName());
        productRepository.save(product);
        return "Product created successfully";
    }

    public List<Product> getAllProducts() {
        log.info("getting all products");
        return productRepository.findAll();
    }
}
