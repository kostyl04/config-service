package com.kostylenko.config_service.config_service_rest.converter.config;

import com.kostylenko.common.common_mapper.domain.converter.BaseConverter;
import com.kostylenko.config_service.config_provider.model.ConfigKey;
import com.kostylenko.config_service.config_provider.model.Parameter;
import com.kostylenko.config_service.config_service_rest.domain.model.Config;
import org.springframework.stereotype.Component;

@Component
public class ConfigToProviderConfigConverter extends BaseConverter<Config, com.kostylenko.config_service.config_provider.model.Config> {

    @Override
    public com.kostylenko.config_service.config_provider.model.Config convert(Config from, com.kostylenko.config_service.config_provider.model.Config to) {
        to.setConfigKey(mapper.map(from.getConfigKey(), ConfigKey.class));
        to.setParameters(mapper.mapToSet(from.getParameters(), Parameter.class));
        return to;
    }
}
