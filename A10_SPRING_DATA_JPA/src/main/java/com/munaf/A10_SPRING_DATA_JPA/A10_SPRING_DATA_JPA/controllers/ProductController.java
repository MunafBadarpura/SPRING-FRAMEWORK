package com.munaf.A10_SPRING_DATA_JPA.A10_SPRING_DATA_JPA.controllers;

import com.munaf.A10_SPRING_DATA_JPA.A10_SPRING_DATA_JPA.entities.ProductEntity;
import com.munaf.A10_SPRING_DATA_JPA.A10_SPRING_DATA_JPA.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/check")
    public String check() {
        return "CHECK IS WORKING";
    }

//    @GetMapping("/")
//    public List<ProductEntity> getAllProductsSortedByName(){
//        return productRepository.findByOrderByPrice();
//    }

    // updation
    @GetMapping("/updateName")
    public String updateNamess(@RequestParam(required = false) String prevName,
                               @RequestParam(required = false) String newName) {

        if (prevName == null || newName == null) throw new RuntimeException();
        int rowAffected = productRepository.updateNamesByName(prevName, newName);
        if (rowAffected > 0) {
            return "Updated " + prevName + " to " + newName +" with " +rowAffected +" rows affected";
        }
        return "Nothing Affected";
    }

    // Sorting
    @GetMapping("/")
    public List<ProductEntity> getSortedProducts(@RequestParam(defaultValue = "id") String sortBy){
        // below line say that sort in desc order and let say sortBy is a name and two products with same name exist
        // then they are sorted on price we will add many more fields that we want
//        return productRepository.findBy(Sort.by(
//                Sort.Direction.DESC,
//                sortBy,
//                "price"
//        ));

        // another way of creating sort
        return productRepository.findAll(Sort.by(
                Sort.Order.asc(sortBy),
                Sort.Order.asc("price")
        ));
    }

    // Pagination
    @GetMapping("/pagination/")
    public List<ProductEntity> getPaginationProducts(@RequestParam(defaultValue = "id") String sortBy,
                                                     @RequestParam(defaultValue = "0") Integer pageNumber){

        Pageable pageable = PageRequest.of(
                pageNumber,
                4,
                Sort.by(sortBy, "price").ascending()
        );
        return productRepository.findAll(pageable).getContent();

    }

    @GetMapping("/findAllByName")
    public List<ProductEntity> findByNames(@RequestParam(defaultValue = "id") String sortBy,
                                           @RequestParam(defaultValue = "") String name,
                                           @RequestParam(defaultValue = "0") Integer pageNumber){


        return productRepository.findByNameIgnoreCaseContains(
                name,
                PageRequest.of(pageNumber, 4, Sort.by(sortBy))
        );

    }






}
