package com.example.SpringBoot1.model;

import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer{
    public void compile(){
        System.out.println("Compile Using Laptop");
    }
}
