package net.caimito.multitenant.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.caimito.multitenant.Data;

@Service
public class DataService {

	@Autowired
	private DataRepository dataRepository ;
	
	// TODO fake implementation: start using the tenantId
	public List<Data> findDataForTenant(String tenantId) {
		return dataRepository.findAll() ;
	}

}
