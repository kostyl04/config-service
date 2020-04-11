package com.kostylenko.config_service.config_service_rest.data.repository;

import com.kostylenko.config_service.config_service_rest.data.entity.Config;
import com.kostylenko.config_service.config_service_rest.data.model.ConfigKey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends MongoRepository<Config, String> {

    @Query(value = "{ 'configKey.appName': ?#{#configKey.appName}," +
            " 'configKey.configName': ?#{#configKey.configName}," +
            " 'configKey.version': ?#{#configKey.version} }", exists = true)
    boolean existsByConfigKey(@Param("configKey") ConfigKey configKey);

    @Query("{ 'configKey.appName': ?#{#configKey.appName}," +
            " 'configKey.configName': ?#{#configKey.configName}," +
            " 'configKey.version': ?#{#configKey.version} }")
    Config getByConfigKey(@Param("configKey") ConfigKey configKey);

}
