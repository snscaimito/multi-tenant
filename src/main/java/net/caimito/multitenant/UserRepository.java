package net.caimito.multitenant;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	
	@Autowired
	private BCryptPasswordEncoder encoder ;

	private static Map<String, ApplicationUser> users = new HashMap<>() ;


	@PostConstruct
	public void load() {
		users.put("hans", new ApplicationUser("hans", encoder.encode("geheim"), "hans")) ;
		users.put("peter", new ApplicationUser("peter", encoder.encode("supergeheim"), "peter")) ;
	}
	
	public void save(ApplicationUser user) {
		users.put(user.getUsername(), user) ;
	}

	public ApplicationUser findByUsername(String username) {
		return users.get(username) ;
	}

}
