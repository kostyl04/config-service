package com.kostylenko.config_service.config_service_rest.converter.config_key;

import com.kostylenko.common.common_mapper.domain.converter.BaseConverter;
import com.kostylenko.config_service.config_service_rest.domain.model.ConfigKey;
import com.kostylenko.config_service.config_service_rest.domain.model.ParameterKey;
import org.springframework.stereotype.Component;

@Component
public class ParameterKeyToConfigKeyConverter extends BaseConverter<ParameterKey, ConfigKey> {

    @Override
    public ConfigKey convert(ParameterKey from, ConfigKey to) {
        to.setAppName(from.getAppName());
        to.setConfigName(from.getConfigName());
        to.setVersion(from.getVersion());
        return to;
    }
}
