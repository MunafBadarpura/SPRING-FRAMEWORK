package com.munaf.SPRING_AOP;

import com.munaf.SPRING_AOP.services.AnujAspectService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SpringAopApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	AnujAspectService anujAspectService;

	@Test
	void testSaveProduct() {
		anujAspectService.saveProduct();
	}


	@Test
	void testSaveProductWithId() {
		String s = anujAspectService.saveProduct(1);
		log.info(s);
	}

	@Test
	void throwSaveMetthod() {
		anujAspectService.saveProductException();
	}
}
