package com.muanf.A38_REACTIVE_PROGRAMMING_ADVANCE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

@SpringBootApplication
@EnableR2dbcAuditing
public class A38ReactiveProgrammingAdvanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(A38ReactiveProgrammingAdvanceApplication.class, args);
	}

}
