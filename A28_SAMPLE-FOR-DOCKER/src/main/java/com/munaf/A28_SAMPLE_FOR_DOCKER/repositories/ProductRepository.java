package com.munaf.A28_SAMPLE_FOR_DOCKER.repositories;

import com.munaf.A28_SAMPLE_FOR_DOCKER.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
