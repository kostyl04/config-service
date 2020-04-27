package com.kostylenko.config_service.config_service_rest.converter.field;

import com.kostylenko.common.common_mapper.domain.converter.BaseConverter;
import com.kostylenko.config_service.config_service_rest.data.model.Type;
import com.kostylenko.config_service.config_service_rest.domain.model.Field;
import org.springframework.stereotype.Component;

@Component
public class FieldToDataFieldConverter extends BaseConverter<Field, com.kostylenko.config_service.config_service_rest.data.model.Field> {

    @Override
    public com.kostylenko.config_service.config_service_rest.data.model.Field convert(Field from, com.kostylenko.config_service.config_service_rest.data.model.Field to) {
        to.setName(from.getName());
        to.setKey(from.isKey());
        if (to.isKey()) {
            to.setNullable(false);
            to.setImmutable(true);
        } else {
            to.setNullable(from.isNullable());
            to.setImmutable(from.isImmutable());
        }
        to.setType(mapper.map(from.getType(), Type.class));
        return to;
    }
}