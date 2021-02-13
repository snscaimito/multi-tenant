package net.caimito.multitenant.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.caimito.multitenant.Data;
import net.caimito.multitenant.TenantContext;

@Service
@Slf4j
public class DataService {

	@Autowired
	private DataRepository dataRepository ;
	
	public List<Data> findDataForTenant() {
		log.debug(TenantContext.getTenantDataSourceId());
		return dataRepository.findAll() ;
	}

	public Data store(Data data) {
		log.debug(TenantContext.getTenantDataSourceId());
		return dataRepository.save(data) ;
	}

}
