package net.caimito.multitenant.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.caimito.multitenant.ApplicationUser;
import net.caimito.multitenant.UserRepository;

@RestController
@RequestMapping("/greet")
public class HelloController {
	
	@Autowired
	private UserRepository userRepository ;
	
	@GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> greet(@PathVariable String name, Principal principal) {
		ApplicationUser user = userRepository.findByUsername(principal.getName()) ;
		
		return ResponseEntity.ok(new Greeting(name, user.getTenantId())) ;
	}

}
