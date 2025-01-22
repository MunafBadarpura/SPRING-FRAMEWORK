package com.munaf.A13_SPRING_SECURITY_1;

import com.munaf.A13_SPRING_SECURITY_1.entity.User;
import com.munaf.A13_SPRING_SECURITY_1.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class A13SpringSecurity1ApplicationTests {

	@Autowired
	private JwtService jwtService;


	@Test
	void testService () {
		User user = new User(101L,"munaf@gmail.com","0000");
		String jwtToken = jwtService.generateToken(user);
		System.out.println(jwtToken);

		// Get Id From JwtToken
		Long userId = jwtService.getUserIdFromToken(jwtToken);
		System.out.println(userId);
	}



}
