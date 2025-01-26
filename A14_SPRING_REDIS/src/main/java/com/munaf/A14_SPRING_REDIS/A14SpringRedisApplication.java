package com.munaf.A14_SPRING_REDIS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class A14SpringRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(A14SpringRedisApplication.class, args);
	}

}
