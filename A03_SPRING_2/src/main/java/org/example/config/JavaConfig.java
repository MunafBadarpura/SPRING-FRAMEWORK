package org.example.config;

import org.example.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("org.example")
public class JavaConfig {

//    @Bean
//    public Alien alien(Computer com){ // @Autowired, @Qualifier("desktop")
//        Alien alien = new Alien();
//        alien.setAge(20);
//        alien.setCom(com);
//        return alien;
//    }
//
//    // @Bean(name = {"desk1", "desk2", "desk3"}), @Scope("prototype")
//    @Primary
//    public Desktop desktop(){
//        return new Desktop();
//    }
//
//    @Bean
//    public Laptop laptop(){
//        return new Laptop();
//    }
//
//    @Bean
//    public Car car(){
//        return new Car(120);  // constructor injection
//    }
}
