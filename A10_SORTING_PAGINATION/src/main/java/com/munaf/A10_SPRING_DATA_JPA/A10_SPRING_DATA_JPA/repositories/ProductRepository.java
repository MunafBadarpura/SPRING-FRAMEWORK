package com.munaf.A10_SPRING_DATA_JPA.A10_SPRING_DATA_JPA.repositories;

import com.munaf.A10_SPRING_DATA_JPA.A10_SPRING_DATA_JPA.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    // SORTING

    // A. With OrderBy keyword
    List<ProductEntity> findAllByOrderByPrice(); // Default Asc

    List<ProductEntity> findByNameOrderByPriceDesc(String name);

    // B. With Sort class
    List<ProductEntity> findAll(Sort sort);


    // PAGINATION
    Page<ProductEntity> findAll(Pageable pageable);

}
