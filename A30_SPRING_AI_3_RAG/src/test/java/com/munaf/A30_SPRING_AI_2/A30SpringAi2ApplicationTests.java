package com.munaf.A30_SPRING_AI_2;

import com.munaf.A30_SPRING_AI_2.services.DataLoaderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class A30SpringAi2ApplicationTests {

	@Autowired
	private DataLoaderService dataLoaderService;

	@Test
	void contextLoads() {
	}


	@Test
	void loadDataFromJson() {
		dataLoaderService.loadDataFromJson();
	}

}
