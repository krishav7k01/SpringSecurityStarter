package com.krishav.completesetup.completesetup;

import com.krishav.completesetup.completesetup.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.krishav.completesetup.completesetup.services.JwtService;

@SpringBootTest
class CompletesetupApplicationTests {


	@Autowired
	private JwtService jwtService;


	@Test
	void contextLoads() {

		User user = new User(4L , "krishav1@gmail.com", "1234","krishav1");
		String token = jwtService.generateToken(user);

		System.out.println(token);

		Long id = jwtService.getUserIdFromToken(token);

		System.out.println(id);
	}


}
