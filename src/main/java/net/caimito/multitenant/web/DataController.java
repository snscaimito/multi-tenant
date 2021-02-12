package net.caimito.multitenant.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.caimito.multitenant.ApplicationUser;
import net.caimito.multitenant.Data;
import net.caimito.multitenant.UserRepository;
import net.caimito.multitenant.db.DataService;

@RestController
@RequestMapping("/data")
public class DataController {

	@Autowired
	private UserRepository userRepository ;
	
	@Autowired
	private DataService dataService ;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Data>> greet(Principal principal) {
		ApplicationUser user = userRepository.findByUsername(principal.getName()) ;
		
		return ResponseEntity.ok(dataService.findDataForTenant(user.getTenantId())) ;
	}
	
}
