package com.kostylenko.config_service.config_service_rest.data.repository;

import com.mongodb.client.DistinctIterable;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

@Repository
@AllArgsConstructor
public class ApplicationRepository {

    private MongoTemplate mongoTemplate;

    public Set<String> getApplicationNames() {
        DistinctIterable<String> config = mongoTemplate.getCollection("config")
                .distinct("configKey.appName", String.class);
        Set<String> result = new HashSet<>();
        config.forEach((Consumer<? super String>) result::add);
        return result;
    }
}
