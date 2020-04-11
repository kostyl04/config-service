package com.kostylenko.config_service.config_service_rest.converter.config_key;

import com.kostylenko.common.common_mapper.domain.converter.BaseConverter;
import com.kostylenko.config_service.config_service_rest.rest.model.ConfigKey;
import org.springframework.stereotype.Component;

@Component
public class ApiConfigKeyToConfigKeyConverter extends BaseConverter<ConfigKey, com.kostylenko.config_service.config_service_rest.domain.model.ConfigKey> {
    @Override
    public com.kostylenko.config_service.config_service_rest.domain.model.ConfigKey convert(ConfigKey from, com.kostylenko.config_service.config_service_rest.domain.model.ConfigKey to) {
        to.setAppName(from.getAppName());
        to.setConfigName(from.getConfigName());
        to.setVersion(from.getVersion());
        return to;
    }
}
