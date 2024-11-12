package com.example.SpringBoot1.services;

import com.example.SpringBoot1.repo.LaptopRepository;
import com.example.SpringBoot1.model.Laptop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {
    @Autowired
    private LaptopRepository laptopRepository;

    public void addLaptop(Laptop lap) {
        laptopRepository.saveLaptop(lap);
        System.out.println("Method Called");
    }
}
