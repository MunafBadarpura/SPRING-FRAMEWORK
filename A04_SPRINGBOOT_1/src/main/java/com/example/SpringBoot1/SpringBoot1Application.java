package com.example.SpringBoot1;

/*
* SpringApplication, @SpringBootApplication
* @Component
* @Service
* @Repository
* */

import com.example.SpringBoot1.model.Alien;
import com.example.SpringBoot1.model.Laptop;
import com.example.SpringBoot1.services.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBoot1Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBoot1Application.class, args);
//		Alien alien = context.getBean(Alien.class);
//		alien.code();




		LaptopService service = context.getBean(LaptopService.class);

		Laptop lap = context.getBean(Laptop.class);
		service.addLaptop(lap);

	}

}











