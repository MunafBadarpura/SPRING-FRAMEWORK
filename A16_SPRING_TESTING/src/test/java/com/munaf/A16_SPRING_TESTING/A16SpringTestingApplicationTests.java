package com.munaf.A16_SPRING_TESTING;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
@Slf4j
class A16SpringTestingApplicationTests {

//	@BeforeAll // run only 1 time
//	static void setupAll() {
//		log.info("RUN BEFORE ALL TEST");
//	}
//
//	@BeforeEach // run every time
//	void setup() {
//		log.info("RUN BEFORE EACH TEST");
//	}
//
//	@AfterAll // run only 1 time
//	static void afterAll() {
//		log.info("RUN AFTER ALL TEST");
//	}
//
//	@AfterEach // run every time
//	void afterEach() {
//		log.info("RUN AFTER EACH TEST");
//	}


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
		String fruit = "Apple";
		int result = addTwoNumbers(a,b);
//		Assertions.assertEquals(15, result); // jUnit
		assertThat(result)
				.isEqualTo(15)
				.isCloseTo(20, Offset.offset(10));

		assertThat(fruit)
				.startsWith("Ap")
				.endsWith("le")
				.hasSize(5)
				.isNotBlank();

		log.info("testTwoNumbers TEST PASSED");
	}


	// EXCEPTION TESTING

	@Test
	void divideTwoNumbersTest() {
		int a = 5, b = 0;

		Assertions.assertThatThrownBy(() -> divideTwoNumbers(a, b))
				.isInstanceOf(ArithmeticException.class)
				.isInstanceOf(RuntimeException.class) // true because it is parent of ArithmeticException
				.hasMessage("Divide by zero");
	}

	int divideTwoNumbers(int a, int b) {
		try {
			return a/b;
		}catch (ArithmeticException e) {
			log.error("Arithmetic Exception Occurred");
			throw new ArithmeticException("Divide by zero");
		}
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