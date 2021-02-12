package net.caimito.multitenant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class AbstractControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	protected TestRestTemplate restTemplate;

	protected HttpHeaders login(String username, String password) {
		ResponseEntity<String> loginResponse = doLogin(username, password);
		
		HttpHeaders headersForRequest = new HttpHeaders() ;
		String token = loginResponse.getHeaders().getFirst("Authorization") ;
		headersForRequest.add("Authorization", SecurityConstants.TOKEN_PREFIX + token);

		return headersForRequest ;
	}

	private ResponseEntity<String> doLogin(String username, String password) {
		String json = String.format("{ \"username\": \"%s\", \"password\": \"%s\" } ", username, password) ;
	
		HttpHeaders headersForLogin = new HttpHeaders() ;
		headersForLogin.add("Content-Type", "application/json");
		
		HttpEntity<String> request = new HttpEntity<>(json, headersForLogin);
	
		return restTemplate.exchange("/login", HttpMethod.POST, request, String.class) ;
	}

}
