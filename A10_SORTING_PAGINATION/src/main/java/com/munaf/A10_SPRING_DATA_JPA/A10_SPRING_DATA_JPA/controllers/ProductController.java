package com.munaf.A10_SPRING_DATA_JPA.A10_SPRING_DATA_JPA.controllers;

import com.munaf.A10_SPRING_DATA_JPA.A10_SPRING_DATA_JPA.entities.ProductEntity;
import com.munaf.A10_SPRING_DATA_JPA.A10_SPRING_DATA_JPA.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/ping")
    public String healthCheck() {
        return "PONG";
    }

    // SORTING

    // A. With OrderBy keyword
    @GetMapping("/findAllOrderByPrice")
    public List<ProductEntity> findAllByOrderByPrice() {
        return productRepository.findAllByOrderByPrice();
    }

    @GetMapping("/findByNameOrderByPriceDesc/{name}")
    public List<ProductEntity> findByNameOrderByPriceDesc(@PathVariable String name) {
        return productRepository.findByNameOrderByPriceDesc(name);
    }

    // B. With Sort class
    @GetMapping("/findAllWithSort")
    public List<ProductEntity> findAllWithSort(@RequestParam(required = false, defaultValue = "id") String sortBy,
                                               @RequestParam(required = false, defaultValue = "true") boolean asc
                                               ) {
        if (asc == true) {
            return productRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy,"price"));
        } else {
            return productRepository.findAll(Sort.by(Sort.Direction.DESC, sortBy,"price"));
//            return productRepository.findAll(Sort.by(
//                    Sort.Order.asc(sortBy),
//                    Sort.Order.desc("price")
//            ));
        }
    }


    // PAGINATION
    @GetMapping("/findAllWithPagination")
    public Map<String, Object> findAllWithPagination(@RequestParam(required = false, defaultValue = "1") int pageNo,
                                                     @RequestParam(required = false, defaultValue = "10") int size,
                                                     @RequestParam(required = false, defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(pageNo-1, size, Sort.by(sortBy,"id","name","price"));
        Page<ProductEntity> data = productRepository.findAll(pageable);

        HashMap result = new HashMap();
        result.put("data", data.getContent());
        result.put("pageNo", pageNo);
        result.put("totalPage", data.getTotalPages());
        result.put("totalElements", data.getTotalElements());
        return result;
    }

}
