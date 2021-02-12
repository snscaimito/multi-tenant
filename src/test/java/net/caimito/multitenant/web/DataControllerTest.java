package net.caimito.multitenant.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.caimito.multitenant.Data;
import net.caimito.multitenant.db.DataRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DataControllerTest extends AbstractControllerTest {
	
	@Autowired
	private DataRepository dataRepository ;
	
	@Test
	void noDataForHans() {
		ResponseEntity<Data[]> response = restTemplate.exchange("/data", HttpMethod.GET, 
				new HttpEntity<>(login("hans", "geheim")), Data[].class) ;
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK) ;
		assertThat(response.getBody().length).isEqualTo(0) ;
	}

	@Test
	void getDataForHans() {
		Data dataForHans = new Data("For Hans") ;
		dataRepository.save(dataForHans) ;
		
		ResponseEntity<Data[]> response = restTemplate.exchange("/data", HttpMethod.GET, 
				new HttpEntity<>(login("hans", "geheim")), Data[].class) ;
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK) ;
		assertThat(response.getBody().length).isEqualTo(1) ;
	}

	@Test @Disabled("Enable later when multi-tenant is ready")
	void getDataForPeter() {
		ResponseEntity<Data[]> response = restTemplate.exchange("/data", HttpMethod.GET, 
				new HttpEntity<>(login("peter", "supergeheim")), Data[].class) ;
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK) ;
		assertThat(response.getBody().length).isEqualTo(1) ;
	}

}
