package net.caimito.multitenant.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import net.caimito.multitenant.Data;

public interface DataRepository extends MongoRepository<Data, String> {

}
