package com.kostylenko.config_service.config_service_rest.converter.config;

import com.kostylenko.common.common_mapper.domain.converter.BaseConverter;
import com.kostylenko.common.common_mapper.domain.converter.ConverterScope;
import com.kostylenko.config_service.config_service_rest.domain.model.importing.Config;
import org.springframework.stereotype.Component;

@Component
@ConverterScope("import")
public class ImportConfigToConfigConverter extends BaseConverter<Config, com.kostylenko.config_service.config_service_rest.domain.model.Config> {

    @Override
    public com.kostylenko.config_service.config_service_rest.domain.model.Config convert(Config from, com.kostylenko.config_service.config_service_rest.domain.model.Config to) {
        to.setConfigKey(from.getConfigKey());
        to.setMeta(from.getMeta());
        return to;
    }
}
