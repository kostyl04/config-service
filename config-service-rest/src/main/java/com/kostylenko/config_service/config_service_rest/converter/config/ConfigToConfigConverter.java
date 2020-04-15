package com.kostylenko.config_service.config_service_rest.converter.config;

import com.kostylenko.common.common_mapper.domain.converter.BaseConverter;
import com.kostylenko.common.common_mapper.domain.converter.ConverterScope;
import com.kostylenko.config_service.config_service_rest.domain.model.Config;
import org.springframework.stereotype.Component;

@Component
@ConverterScope("update")
public class ConfigToConfigConverter extends BaseConverter<Config, Config> {

    @Override
    public Config convert(Config from, Config to) {
        to.setConfigKey(from.getConfigKey());
        to.setMeta(from.getMeta());
        to.setParameters(from.getParameters());
        return to;
    }
}
