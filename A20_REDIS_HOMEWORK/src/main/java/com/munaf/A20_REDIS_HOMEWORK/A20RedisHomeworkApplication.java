package com.munaf.A20_REDIS_HOMEWORK;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class A20RedisHomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(A20RedisHomeworkApplication.class, args);
	}

}
