package net.caimito.multitenant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationUser {

	private String username ;
	private String password ;
	

	public ApplicationUser() { }
}
