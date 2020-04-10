package com.kostylenko.config_service.config_service_rest.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${mongodb.connection-string}")
    private String mongodbConnectionString;
    @Value("${mongodb.database-name}")
    private String databaseName;

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(mongodbConnectionString);
    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDbFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }

}