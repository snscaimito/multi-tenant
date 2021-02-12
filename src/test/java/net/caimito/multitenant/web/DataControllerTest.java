package net.caimito.multitenant.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.caimito.multitenant.Data;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DataControllerTest extends AbstractControllerTest {
	
	@Test
	void getDataForHans() {
		ResponseEntity<Data> response = restTemplate.exchange("/data", HttpMethod.GET, 
				new HttpEntity<>(login("hans", "geheim")), Data.class) ;
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK) ;
		assertThat(response.getBody().getContent()).isEqualTo("customerOne") ;
	}

	@Test
	void getDataForPeter() {
		ResponseEntity<Data> response = restTemplate.exchange("/data", HttpMethod.GET, 
				new HttpEntity<>(login("peter", "supergeheim")), Data.class) ;
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK) ;
		assertThat(response.getBody().getContent()).isEqualTo("customerTwo") ;
	}

}
