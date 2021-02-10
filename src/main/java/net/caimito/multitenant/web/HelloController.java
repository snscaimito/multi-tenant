package net.caimito.multitenant.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
public class HelloController {
	
	@GetMapping
	public ResponseEntity<Greeting> greet() {
		return ResponseEntity.ok(new Greeting()) ;
	}

}
