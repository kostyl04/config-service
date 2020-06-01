package com.kostylenko.config_service.config_service_rest.converter.field;

import com.kostylenko.common.common_mapper.domain.converter.BaseConverter;
import com.kostylenko.config_service.config_service_rest.data.model.Field;
import com.kostylenko.config_service.config_service_rest.domain.model.Type;
import org.springframework.stereotype.Component;

@Component
public class DataFieldToFieldConverter extends BaseConverter<Field, com.kostylenko.config_service.config_service_rest.domain.model.Field> {

    @Override
    public com.kostylenko.config_service.config_service_rest.domain.model.Field convert(Field from, com.kostylenko.config_service.config_service_rest.domain.model.Field to) {
        to.setName(from.getName());
        to.setKey(from.isKey());
        to.setNullable(from.isNullable());
        to.setImmutable(from.isImmutable());
        to.setIndex(from.getIndex());
        to.setType(mapper.map(from.getType(), Type.class));
        return to;
    }
}