package com.kostylenko.config_service.config_service_rest.data.repository;

import com.kostylenko.config_service.config_service_rest.data.entity.Config;
import com.kostylenko.config_service.config_service_rest.data.model.ConfigKey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends MongoRepository<Config, String> {


    boolean existsByConfigKey(ConfigKey configKey);

    Config findByConfigKey(ConfigKey configKey);

}
