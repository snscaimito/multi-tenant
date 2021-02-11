package net.caimito.multitenant.web;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@WebMvcTest(HelloController.class)
public class HelloControllerTest {
	
	@Autowired
	private MockMvc mockMvc ;
	
	@Test
	void testName() throws Exception {
		String token = JWT.create()
                .withSubject("hans")
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
		
		mockMvc.perform(
				get("/greet/Stephan")
				.header("Authorization", SecurityConstants.TOKEN_PREFIX + token)
			)
		.andExpect(status().isOk())
		.andExpect(jsonPath("message", is("Hello, Stephan"))) ;
	}
	
}
