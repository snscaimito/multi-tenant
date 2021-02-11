package net.caimito.multitenant.web;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import net.caimito.multitenant.ApplicationUser;
import net.caimito.multitenant.UserRepository;

@WebMvcTest(HelloController.class)
public class HelloControllerTest {
	
	@Autowired
	private MockMvc mockMvc ;
	
	@MockBean
	private UserRepository userRepository ;
	
	@Test
	void testName() throws Exception {
		when(userRepository.findByUsername("hans")).thenReturn(new ApplicationUser("hans", null, "customerOne")) ;
		
		String token = new JwtService().create("hans") ;
		
		mockMvc.perform(
				get("/greet/Stephan")
				.header("Authorization", SecurityConstants.TOKEN_PREFIX + token)
			)
		.andExpect(status().isOk())
		.andExpect(jsonPath("message", is("Hello, Stephan")))
		.andExpect(jsonPath("tenant", is("customerOne")));
	}
	
}
