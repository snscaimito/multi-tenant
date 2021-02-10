package net.caimito.multitenant.web;

import lombok.Data;

@Data
public class Greeting {

	private String message ;
	
	public Greeting(String name) {
		this.message = String.format("Hello, %s", name) ;
	}
}
