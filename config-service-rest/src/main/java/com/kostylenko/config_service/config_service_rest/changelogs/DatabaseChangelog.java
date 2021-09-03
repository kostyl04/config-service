package com.kostylenko.config_service.config_service_rest.changelogs;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;

import static com.mongodb.client.model.Indexes.descending;

@ChangeLog
public class DatabaseChangelog {

    private static final IndexOptions UNIQUE_INDEX_OPTION = new IndexOptions().unique(true);

    @ChangeSet(order = "001", id = "VCR-15.1", author = "Kastylenka Daniil")
    public void createConfigIndexes(MongoDatabase db) {
        MongoCollection<Document> configCollection = db.getCollection("config");
        configCollection.createIndex(descending("configKey"), UNIQUE_INDEX_OPTION);
        configCollection.createIndex(descending("configKey.appName", "configKey.configName", "configKey.version"), UNIQUE_INDEX_OPTION);
    }

    @ChangeSet(order = "002", id = "VCR-15.2", author = "Kastylenka Daniil")
    public void createMetaIndexes(MongoDatabase db) {
        MongoCollection<Document> metaCollection = db.getCollection("meta");
        metaCollection.createIndex(descending("name"), UNIQUE_INDEX_OPTION);
    }

    @ChangeSet(order = "003", id = "VCR-15.3", author = "Kastylenka Daniil")
    public void createParameterIndexes(MongoDatabase db) {
        MongoCollection<Document> parameterCollection = db.getCollection("parameter");
        parameterCollection.createIndex(descending("parameterKey"), UNIQUE_INDEX_OPTION);
        parameterCollection.createIndex(descending("parameterKey.name", "parameterKey.appName", "parameterKey.configName", "parameterKey.version"), UNIQUE_INDEX_OPTION);
    }
}
