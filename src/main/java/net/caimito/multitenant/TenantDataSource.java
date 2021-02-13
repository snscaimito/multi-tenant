package net.caimito.multitenant;

import lombok.AllArgsConstructor;

@lombok.Data
@AllArgsConstructor
public class TenantDataSource {

	private String host ;
	private int port ;
	private String databaseName ;
	
}
