package com.kostylenko.config_service.config_service_rest.converter.config;

import com.kostylenko.common.common_mapper.domain.converter.BaseConverter;
import com.kostylenko.config_service.config_service_rest.domain.model.ConfigKey;
import com.kostylenko.config_service.config_service_rest.domain.model.Meta;
import com.kostylenko.config_service.config_service_rest.domain.model.Parameter;
import com.kostylenko.config_service.config_service_rest.rest.model.Config;
import org.springframework.stereotype.Component;

@Component
public class ApiConfigToConfigConverter extends BaseConverter<Config, com.kostylenko.config_service.config_service_rest.domain.model.Config> {

    @Override
    public com.kostylenko.config_service.config_service_rest.domain.model.Config convert(Config from, com.kostylenko.config_service.config_service_rest.domain.model.Config to) {
        to.setConfigKey(mapper.map(from.getConfigKey(), ConfigKey.class));
        Meta meta = new Meta();
        meta.setName(from.getMetaName());
        to.setMeta(meta);
        to.setParameters(mapper.mapToSet(from.getParameters(), Parameter.class));
        return to;
    }
}
