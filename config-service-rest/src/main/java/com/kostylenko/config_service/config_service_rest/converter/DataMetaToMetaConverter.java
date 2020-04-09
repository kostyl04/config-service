package com.kostylenko.config_service.config_service_rest.converter;

import com.kostylenko.common.common_mapper.domain.converter.BaseConverter;
import com.kostylenko.config_service.config_service_rest.data.entity.Meta;
import com.kostylenko.config_service.config_service_rest.domain.model.Field;
import org.springframework.stereotype.Component;

@Component
public class DataMetaToMetaConverter extends BaseConverter<Meta, com.kostylenko.config_service.config_service_rest.domain.model.Meta> {

    @Override
    public com.kostylenko.config_service.config_service_rest.domain.model.Meta convert(Meta from, com.kostylenko.config_service.config_service_rest.domain.model.Meta to) {
        to.setName(from.getName());
        to.setFields(mapper.mapToSet(from.getFields(), Field.class));
        to.setKeyDelimiter(from.getKeyDelimiter());
        return to;
    }
}
