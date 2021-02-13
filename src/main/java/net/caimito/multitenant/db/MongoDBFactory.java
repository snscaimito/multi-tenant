package net.caimito.multitenant.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

@Configuration
public class MongoDBFactory extends SimpleMongoClientDatabaseFactory {
	@Autowired
	MongoDataSources mongoDataSources;

	public MongoDBFactory(@Qualifier("getMongoClient") MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
    }

	@Override
	protected MongoDatabase doGetMongoDatabase(String dbName) {
		return mongoDataSources.mongoDatabaseCurrentTenantResolver();
	}
}
