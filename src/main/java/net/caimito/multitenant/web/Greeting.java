package net.caimito.multitenant.web;

import lombok.Data;

@Data
public class Greeting {

	private String message ;
	private String tenant ;
	
	public Greeting(String name, String tenant) {
		this.message = String.format("Hello, %s", name) ;
		this.tenant = tenant ;
	}
}
