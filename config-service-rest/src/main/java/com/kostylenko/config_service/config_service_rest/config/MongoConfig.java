package com.kostylenko.config_service.config_service_rest.config;

import com.github.mongobee.Mongobee;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.lang.NonNull;
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

    private static final String CHANGELOGS_PACKAGE = "com.kostylenko.config_service.config_service_rest.changelogs";

    @NonNull
    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(mongodbConnectionString);
    }

    @NonNull
    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Bean
    public Mongobee mongobee() {
        Mongobee runner = new Mongobee(mongodbConnectionString);
        runner.setDbName(databaseName);
        runner.setChangeLogsScanPackage(CHANGELOGS_PACKAGE);
        return runner;
    }
}