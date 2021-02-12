package net.caimito.multitenant.db;

import org.springframework.stereotype.Service;

import net.caimito.multitenant.Data;

@Service
public class DataService {

	public Data findDataForTenant(String tenantId) {
		return new Data(tenantId) ; // TODO fake
	}

}
