package com.example.SpringBoot1.repo;

import com.example.SpringBoot1.model.Laptop;
import org.springframework.stereotype.Repository;

@Repository
public class LaptopRepository {
    public void saveLaptop(Laptop lap){
        System.out.println("Laptop Saved In Database");
    }
}
