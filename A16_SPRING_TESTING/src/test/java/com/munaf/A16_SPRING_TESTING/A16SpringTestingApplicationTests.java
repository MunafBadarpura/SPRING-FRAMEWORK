package com.munaf.A16_SPRING_TESTING;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
@Slf4j
class A16SpringTestingApplicationTests {

	@BeforeAll // run only 1 time
	static void setupAll() {
		log.info("RUN BEFORE ALL");
	}

	@BeforeEach // run every time
	void setup() {
		log.info("RUN BEFORE EACH");
	}

	@AfterAll // run only 1 time
	static void afterAll() {
		log.info("RUN AFTER ALL");
	}

	@AfterEach // run every time
	void afterEach() {
		log.info("RUN AFTER EACH");
	}


	@Test
	void contextLoads() {
		log.info("Test No 1");
	}

	@Test
	void test2() {
		log.info("Test No 2");
	}

	@Test
	@Disabled
	@DisplayName("TestingForSumOfNumber")
	void sumOfNumberTest() {
		log.info("Test No 2");
	}


	int addTwoNumbers(int a, int b) {
		return a+b;
	}

	@Test
	void testTwoNumbers() {
		int a = 10, b = 5;
		int result = addTwoNumbers(a,b);
//		Assertions.assertEquals(15, result); // jUnit
		assertThat(result)
				.isEqualTo(15)
				.isCloseTo(20, Offset.offset(10));

		assertThat("Apple")
				.startsWith("Ap")
				.endsWith("le")
				.hasSize(5);
	}

}


/*
• Common Junit Annotations
• @Test: Marks a method as a test method. JUnit will execute this method when running tests.
• @DisplayName: Sets a custom display name for the test class or test method. This name is used in test reports and IDEs.
• @Disabled: Disables a test class or test method. Disabled tests are not executed.


• @BeforeEach: Indicates that the annotated method should be executed before each test method. These can be used to reset each test case conditions.
• @AfterEach: Indicates that the annotated method should be executed after each test method.
• @BeforeAll: Indicates that the annotated method should be executed once before all test methods in the class. The method must be static. (Executed once)
• @AfterAll: Indicates that the annotated method should be executed once after all test methods in the class. The method must be static. (Executed once)

*/