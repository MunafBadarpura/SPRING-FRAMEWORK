package com.munaf.A10_SPRING_DATA_JPA.A10_SPRING_DATA_JPA.repositories;

import com.munaf.A10_SPRING_DATA_JPA.A10_SPRING_DATA_JPA.entities.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    // For save, read and delete we have this type of keywords but for update and create we need to implement own query

    Optional<ProductEntity> findByName(String name);

    List<ProductEntity> findByCreatedAtAfter(LocalDateTime before);

    List<ProductEntity> findByNameOrPrice(String name, BigDecimal price);

    // match the price and name but price is less than provided
    List<ProductEntity> findByNameAndPriceLessThan(String name, BigDecimal price);

//    List<ProductEntity> findByNameIgnoreCaseContains(String p);

    Integer countByPriceLessThan(BigDecimal price);

    @Transactional // for transactional operations this anno is required
    Integer deleteByPrice(BigDecimal price);

    // creating own queries = use java names
    @Query("select e from ProductEntity e where e.name=?1 and e.sku=?2")
    List<ProductEntity> findByNameAnsSku(String name, String sku);


    // creating sql query = use sql names
    @Query(value = "select * from product_table where product_name=?1", nativeQuery = true)
    List<ProductEntity> findAllByName(String name);


    // Soring
    List<ProductEntity> findByOrderByPrice();

    List<ProductEntity> findBy(Sort sort);

    List<ProductEntity> findByNameIgnoreCaseContains(String name, Pageable pageable);


    // updation
    @Modifying
    @Transactional
    @Query(value = "update test.product_table SET product_name=?2 where product_name=?1", nativeQuery = true)
    int updateNamesByName(String prevName, String newName);



}
