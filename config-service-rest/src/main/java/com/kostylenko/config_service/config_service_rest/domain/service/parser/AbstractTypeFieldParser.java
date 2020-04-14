package com.kostylenko.config_service.config_service_rest.domain.service.parser;

import com.kostylenko.config_service.config_service_rest.domain.model.Field;
import com.kostylenko.config_service.config_service_rest.domain.model.Type;

import static java.util.Objects.isNull;

public abstract class AbstractTypeFieldParser<P> implements TypeFieldValidatableParser<P> {

    private final Type supportedType;

    public AbstractTypeFieldParser(Type supportedType) {
        this.supportedType = supportedType;
    }

    @Override
    public boolean accepts(Field field, Object value) {
        if (isNull(value)) {
            return false;
        }
        return field.getType() == supportedType;
    }
}
