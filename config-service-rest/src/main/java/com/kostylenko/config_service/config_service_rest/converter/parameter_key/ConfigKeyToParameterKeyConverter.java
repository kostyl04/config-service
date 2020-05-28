package com.kostylenko.config_service.config_service_rest.converter.parameter_key;

import com.kostylenko.common.common_mapper.domain.converter.BaseConverter;
import com.kostylenko.config_service.config_service_rest.domain.model.ConfigKey;
import com.kostylenko.config_service.config_service_rest.domain.model.ParameterKey;
import org.springframework.stereotype.Component;

@Component
public class ConfigKeyToParameterKeyConverter extends BaseConverter<ConfigKey, ParameterKey> {

    @Override
    public ParameterKey convert(ConfigKey from, ParameterKey to) {
        to.setAppName(from.getAppName());
        to.setConfigName(from.getConfigName());
        to.setVersion(from.getVersion());
        return to;
    }
}
