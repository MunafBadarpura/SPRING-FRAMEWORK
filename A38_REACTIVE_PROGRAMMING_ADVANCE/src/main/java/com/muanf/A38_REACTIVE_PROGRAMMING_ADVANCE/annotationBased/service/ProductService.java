package com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.service;

import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.dto.ProductCreateRequest;
import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.dto.ProductResponse;
import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.entity.Product;
import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.exception.ResourceAlreadyExistsException;
import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.exception.ResourceNotFoundException;
import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductService {

    ProductRepository productRepository;

    public Mono<ProductResponse> addProduct(Mono<ProductCreateRequest> requestMono) {

        return requestMono.flatMap(request ->

                productRepository.existsByName(request.name())
                        .flatMap(isExists -> {

                            if (isExists) {
                                return Mono.error(new ResourceAlreadyExistsException("Product already exists"));
                            }

                            Product product = Product.builder()
                                    .name(request.name())
                                    .price(request.price())
                                    .build();

                            return productRepository.save(product)
                                    .map(saved -> ProductResponse.builder()
                                            .id(saved.getId().toString())
                                            .name(saved.getName())
                                            .price(saved.getPrice())
                                            .createdAt(saved.getCreatedAt())
                                            .updatedAt(saved.getUpdatedAt())
                                            .build());
                        })

        );
    }

    public Mono<ProductResponse> addProductWithFunctional(ProductCreateRequest request) {
        productRepository.existsByName(request.name())
                .flatMap(isExists -> {

                    if (isExists) {
                        return Mono.error(new ResourceAlreadyExistsException("Product already exists"));
                    }

                    Product product = Product.builder()
                            .name(request.name())
                            .price(request.price())
                            .build();

                    return productRepository.save(product)
                            .map(saved -> ProductResponse.builder()
                                    .id(saved.getId().toString())
                                    .name(saved.getName())
                                    .price(saved.getPrice())
                                    .createdAt(saved.getCreatedAt())
                                    .updatedAt(saved.getUpdatedAt())
                                    .build());
                });

        return Mono.empty();
    }


    public Mono<ProductResponse> getProductById(String id) {
        return productRepository.findById(UUID.fromString(id))
                .map(product -> {
                    return ProductResponse.builder()
                            .id(product.getId().toString())
                            .name(product.getName())
                            .price(product.getPrice())
                            .createdAt(product.getCreatedAt())
                            .updatedAt(product.getUpdatedAt())
                            .build();
                })
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Product not found with id : " + id)));
    }

    public Mono<Page<ProductResponse>> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Flux<Product> products = productRepository.findAllBy(pageable);
        Mono<Long> total = productRepository.count();

        return products.collectList()
                .zipWith(total)
                .map(tuple -> {

                    List<ProductResponse> responses = tuple.getT1().stream()
                            .map(product -> ProductResponse.builder()
                                    .id(product.getId().toString())
                                    .name(product.getName())
                                    .price(product.getPrice())
                                    .createdAt(product.getCreatedAt())
                                    .updatedAt(product.getUpdatedAt())
                                    .build())
                            .toList();

                    return new PageImpl<>(responses, pageable, tuple.getT2());

                });
    }

    public Mono<Void> deleteProduct(String id) {
        return productRepository.existsById(UUID.fromString(id))
                .flatMap(isExists -> {
                    if (!isExists) {
                        throw new ResourceNotFoundException("Product not found with id : " + id);
                    }
                    return productRepository.deleteById(UUID.fromString(id));
                });
    }

}
