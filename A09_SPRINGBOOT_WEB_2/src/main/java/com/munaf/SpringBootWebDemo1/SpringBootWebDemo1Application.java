package com.munaf.SpringBootWebDemo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
1. @RestController : this annotation is shorthand for @Controller and @ResponseBody, meaning all methods in controller will return json/xml
					 directly to the response body.

2. @PathVariable : {empId} jab ham ese parameter lete hai
3. @RequestParam : jab ham form or ? through parameter bhejte hai
4. @RequestBody : jab ham postman se json data bheje
*/


@SpringBootApplication
public class SpringBootWebDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebDemo1Application.class, args);
	}

}
