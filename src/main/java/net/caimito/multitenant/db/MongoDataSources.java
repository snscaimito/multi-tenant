package net.caimito.multitenant.db;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import lombok.extern.slf4j.Slf4j;
import net.caimito.multitenant.TenantContext;
import net.caimito.multitenant.TenantDataSource;

@Component
@Slf4j
public class MongoDataSources {
	private static Map<String, TenantDataSource> tenantDataSources = new HashMap<String, TenantDataSource>() ;

	static {
		tenantDataSources.put("hans", new TenantDataSource("localhost", 27018, "admin-db")) ;
		tenantDataSources.put("peter", new TenantDataSource("localhost", 27019, "admin-db")) ;
	}
	
	@Bean
	public String databaseName() {
		return "admin-db";
	}

	@Bean
	public MongoClient getMongoClient() {
		MongoCredential credential = MongoCredential.createCredential("admin", "admin-db", "geheim".toCharArray());

		return MongoClients.create(MongoClientSettings.builder()
				.applyToClusterSettings(
						builder -> builder.hosts(Collections.singletonList(new ServerAddress("localhost", 27017))))
				.credential(credential).build());
	}

	public MongoDatabase mongoDatabaseCurrentTenantResolver() {
		final String dataSourceId = TenantContext.getTenantDataSourceId();
		
		TenantDataSource dataSource = tenantDataSources.get(dataSourceId) ;

		MongoCredential credential = MongoCredential.createCredential("admin", "admin-db", "geheim".toCharArray());

		log.debug("Using datasource {}", dataSource);
		log.debug("Mongo credentials {}", credential);
		
		return MongoClients.create(MongoClientSettings.builder()
				.applyToClusterSettings(
						builder -> builder.hosts(Collections.singletonList(new ServerAddress(dataSource.getHost(), dataSource.getPort()))))
				.credential(credential).build()).getDatabase(dataSource.getDatabaseName());
	}
}
