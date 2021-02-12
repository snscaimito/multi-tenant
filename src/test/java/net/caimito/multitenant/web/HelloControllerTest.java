package net.caimito.multitenant.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void greetHansFromCustomerOne() throws Exception {
		String token = login("hans", "geheim") ;
		
		HttpHeaders headers = new HttpHeaders() ;
		headers.add("Authorization", SecurityConstants.TOKEN_PREFIX + token);
		
		HttpEntity<Greeting> request = new HttpEntity<>(headers);
		
		ResponseEntity<Greeting> response = restTemplate.exchange("/greet", HttpMethod.GET, request, Greeting.class) ;

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK) ;
		assertThat(response.getBody().getMessage()).isEqualTo("Hello, hans") ;
		assertThat(response.getBody().getTenant()).isEqualTo("customerOne") ;
	}

	private String login(String username, String password) {
		String json = String.format("{ \"username\": \"%s\", \"password\": \"%s\" } ", username, password) ;

		HttpHeaders headers = new HttpHeaders() ;
		headers.add("Content-Type", "application/json");
		
		HttpEntity<String> request = new HttpEntity<>(json, headers);

		ResponseEntity<String> response = restTemplate.exchange("/login", HttpMethod.POST, request, String.class) ;
		
		return response.getHeaders().getFirst("Authorization") ;
	}

	@Test
	void greetPeterFromCustomerTwo() throws Exception {
		String token = login("peter", "supergeheim") ;
		
		HttpHeaders headers = new HttpHeaders() ;
		headers.add("Authorization", SecurityConstants.TOKEN_PREFIX + token);
		
		HttpEntity<Greeting> request = new HttpEntity<>(headers);
		
		ResponseEntity<Greeting> response = restTemplate.exchange("/greet", HttpMethod.GET, request, Greeting.class) ;

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK) ;
		assertThat(response.getBody().getMessage()).isEqualTo("Hello, peter") ;
		assertThat(response.getBody().getTenant()).isEqualTo("customerTwo") ;
	}

}
