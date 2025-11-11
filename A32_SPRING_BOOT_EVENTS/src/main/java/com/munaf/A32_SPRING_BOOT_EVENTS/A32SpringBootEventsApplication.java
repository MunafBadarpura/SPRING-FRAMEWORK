package com.munaf.A32_SPRING_BOOT_EVENTS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync // to enable async
public class A32SpringBootEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(A32SpringBootEventsApplication.class, args);
	}

}
