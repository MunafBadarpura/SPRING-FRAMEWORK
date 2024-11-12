package org.example;

import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer{
    public Laptop(){
        System.out.println("LAPTOP CREATED");
    }

    @Override
    public void compile(){
        System.out.println("COMPILE USING LAPTOP");
    }
}
