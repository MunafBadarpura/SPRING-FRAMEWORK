package org.example;

import org.springframework.stereotype.Component;

@Component
public class Desktop implements Computer{
    public Desktop(){
        System.out.println("DESKTOP CREATED");
    }

    @Override
    public void compile(){
        System.out.println("COMPILE USING DESKTOP");
    }
}
