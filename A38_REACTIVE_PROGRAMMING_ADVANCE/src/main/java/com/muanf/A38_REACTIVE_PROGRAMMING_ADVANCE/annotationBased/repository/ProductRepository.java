package com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.repository;

import com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE.annotationBased.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductRepository extends ReactiveCrudRepository<Product, UUID>, ReactiveSortingRepository<Product, UUID> {
    Mono<Boolean> existsByName(String name);

    Flux<Product> findAllBy(Pageable pageable);
}


// ReactiveSortingRepository<Product, UUID> : Only gives one method : Flux<T> findAll(Sort sort);