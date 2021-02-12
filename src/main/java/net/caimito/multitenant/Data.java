package net.caimito.multitenant;

@lombok.Data
public class Data {

	private String content ;
	
	private Data() {}
	
	public Data(String content) {
		this.content = content ;
	}
	
}
