package net.caimito.multitenant.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloControllerTest extends AbstractControllerTest {

	@Test
	void greetHansFromCustomerOne() throws Exception {
		HttpEntity<Greeting> request = new HttpEntity<>(login("hans", "geheim"));
		
		ResponseEntity<Greeting> response = restTemplate.exchange("/greet", HttpMethod.GET, request, Greeting.class) ;

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK) ;
		assertThat(response.getBody().getMessage()).isEqualTo("Hello, hans") ;
		assertThat(response.getBody().getTenant()).isEqualTo("customerOne") ;
	}

	@Test
	void greetPeterFromCustomerTwo() throws Exception {
		HttpEntity<Greeting> request = new HttpEntity<>(login("peter", "supergeheim"));
		
		ResponseEntity<Greeting> response = restTemplate.exchange("/greet", HttpMethod.GET, request, Greeting.class) ;

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK) ;
		assertThat(response.getBody().getMessage()).isEqualTo("Hello, peter") ;
		assertThat(response.getBody().getTenant()).isEqualTo("customerTwo") ;
	}

}
