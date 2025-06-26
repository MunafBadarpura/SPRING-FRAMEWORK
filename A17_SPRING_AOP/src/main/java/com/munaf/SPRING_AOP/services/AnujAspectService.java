package com.munaf.SPRING_AOP.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AnujAspectService {

    public String saveProduct(int productId) {
        log.info("CALLING SaveProductWithId");
        return "saveProductWithId";
    }


    public void saveProduct() {
        System.out.println("CALLING");
        System.out.println("saveProduct");
        System.out.println("CALLED");
    }


    public void saveProductException() {
        System.out.println("CALLING");
        throw new RuntimeException("exception in saveProductException method");
    }

}
