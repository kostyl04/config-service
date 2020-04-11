package com.kostylenko.config_service.config_service_rest.converter.config;

import com.kostylenko.common.common_mapper.domain.converter.BaseConverter;
import com.kostylenko.config_service.config_service_rest.domain.model.Config;
import com.kostylenko.config_service.config_service_rest.rest.model.ConfigKey;
import com.kostylenko.config_service.config_service_rest.rest.model.Meta;
import com.kostylenko.config_service.config_service_rest.rest.model.Parameter;
import org.springframework.stereotype.Component;

@Component
public class ConfigToApiConfigConverter extends BaseConverter<Config, com.kostylenko.config_service.config_service_rest.rest.model.Config> {

    @Override
    public com.kostylenko.config_service.config_service_rest.rest.model.Config convert(Config from, com.kostylenko.config_service.config_service_rest.rest.model.Config to) {
        to.setConfigKey(mapper.map(from.getConfigKey(), ConfigKey.class));
        to.setMeta(mapper.map(from.getMeta(), Meta.class));
        to.setParameters(mapper.mapToList(from.getParameters(), Parameter.class));
        return to;
    }
}
