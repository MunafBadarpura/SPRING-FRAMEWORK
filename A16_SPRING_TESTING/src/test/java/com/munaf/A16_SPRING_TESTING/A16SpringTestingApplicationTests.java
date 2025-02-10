package com.munaf.A16_SPRING_TESTING;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class A16SpringTestingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	@Disabled
	@DisplayName("TestingForSumOfNumber")
	void sumOfNumberTest() {

	}

}


/*
• Common Junit Annotations
• @Test: Marks a method as a test method. JUnit will execute this method when running tests.
• @DisplayName: Sets a custom display name for the test class or test method. This name is used in test reports and IDEs.
• @Disabled: Disables a test class or test method. Disabled tests are not executed.

*/