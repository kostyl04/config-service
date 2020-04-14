package com.kostylenko.config_service.config_service_rest.domain.service.validator;

import com.kostylenko.config_service.config_service_rest.domain.model.Field;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class NullableFieldValidator implements FieldValidator {

    @Override
    public boolean validate(Field field, Object value) {
        return !isNull(value) || field.isNullable();
    }
}
