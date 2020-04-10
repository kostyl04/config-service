package com.kostylenko.config_service.config_service_rest.converter;

import com.kostylenko.common.common_mapper.domain.converter.BaseConverter;
import com.kostylenko.config_service.config_service_rest.domain.model.Type;
import com.kostylenko.config_service.config_service_rest.rest.model.Field;
import org.springframework.stereotype.Component;

@Component
public class ApiFieldToFieldConverter extends BaseConverter<Field, com.kostylenko.config_service.config_service_rest.domain.model.Field> {

    @Override
    public com.kostylenko.config_service.config_service_rest.domain.model.Field convert(Field from, com.kostylenko.config_service.config_service_rest.domain.model.Field to) {
        to.setName(from.getName());
        to.setKey(from.isKey());
        if (to.isKey()) {
            to.setNullable(false);
        } else {
            to.setNullable(from.isNullable());
        }
        to.setType(mapper.map(from.getType(), Type.class));
        return to;
    }
}