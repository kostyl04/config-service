package com.kostylenko.config_service.config_service_rest.converter.config;

import com.kostylenko.common.common_mapper.domain.converter.BaseConverter;
import com.kostylenko.config_service.config_service_rest.data.entity.Meta;
import com.kostylenko.config_service.config_service_rest.data.entity.Parameter;
import com.kostylenko.config_service.config_service_rest.data.model.ConfigKey;
import com.kostylenko.config_service.config_service_rest.domain.model.Config;
import org.springframework.stereotype.Component;

@Component
public class ConfigToDataConfigConverter extends BaseConverter<Config, com.kostylenko.config_service.config_service_rest.data.entity.Config> {
    @Override
    public com.kostylenko.config_service.config_service_rest.data.entity.Config convert(Config from, com.kostylenko.config_service.config_service_rest.data.entity.Config to) {
        to.setId(from.getId());
        to.setConfigKey(mapper.map(from.getConfigKey(), ConfigKey.class));
        to.setMeta(mapper.map(from.getMeta(), Meta.class));
        to.setParameters(mapper.mapToList(from.getParameters(), Parameter.class));
        return to;
    }
}
