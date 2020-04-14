package com.kostylenko.config_service.config_service_rest.domain.service;

import com.kostylenko.common.common_http.exception.BadRequestApiException;
import com.kostylenko.common.common_mapper.domain.mapper.Mapper;
import com.kostylenko.config_service.config_service_rest.domain.model.ConfigKey;
import com.kostylenko.config_service.config_service_rest.domain.model.Meta;
import com.kostylenko.config_service.config_service_rest.data.repository.ConfigRepository;
import com.kostylenko.config_service.config_service_rest.data.repository.MetaRepository;
import com.kostylenko.config_service.config_service_rest.domain.model.Config;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages.*;
import static java.util.Objects.isNull;

@Slf4j
@Service
@AllArgsConstructor
public class ConfigService {

    private ConfigRepository configRepository;
    private MetaRepository metaRepository;
    private Mapper mapper;

    public Config createConfig(Config config) {
        boolean exists = configRepository.existsByConfigKey(mapper.map(config.getConfigKey(), com.kostylenko.config_service.config_service_rest.data.model.ConfigKey.class));
        if (exists) {
            log.warn("Config already exists: {}", config.getConfigKey());
            throw new BadRequestApiException(CONFIG_ALREADY_EXISTS);
        }
        String metaName = config.getMeta().getName();
        var dataMeta = metaRepository.findByName(metaName);
        if (isNull(dataMeta)) {
            log.warn("Meta not found: {}", metaName);
            throw new BadRequestApiException(META_NOT_FOUND);
        }
        config.setMeta(mapper.map(dataMeta, Meta.class));
        var savedConfig = configRepository.save(mapper.map(config, com.kostylenko.config_service.config_service_rest.data.entity.Config.class));
        return mapper.map(savedConfig, Config.class);
    }

    public Config getConfig(ConfigKey configKey) {
        var dataConfigKey = mapper.map(configKey, com.kostylenko.config_service.config_service_rest.data.model.ConfigKey.class);
        return mapper.map(configRepository.getByConfigKey(dataConfigKey), Config.class);
    }

    @SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
    public Config updateConfig(Config config) {
        boolean exists = configRepository.existsByConfigKey(mapper.map(config.getConfigKey(), com.kostylenko.config_service.config_service_rest.data.model.ConfigKey.class));
        if (!exists) {
            throw new BadRequestApiException(NO_CONFIG_TO_UPDATE);
        }
        var updatedConfig = configRepository.save(mapper.map(config, com.kostylenko.config_service.config_service_rest.data.entity.Config.class));
        return mapper.map(updatedConfig, Config.class);
    }

}
