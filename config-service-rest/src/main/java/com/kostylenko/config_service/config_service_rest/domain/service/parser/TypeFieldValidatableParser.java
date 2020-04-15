package com.kostylenko.config_service.config_service_rest.domain.service.parser;

import com.kostylenko.config_service.config_service_rest.domain.model.Field;
import com.kostylenko.config_service.config_service_rest.domain.service.exception.TypeFieldParseException;

public interface TypeFieldValidatableParser<P> {

    P parse(Object value) throws TypeFieldParseException;

    boolean accepts(Field field, Object value);
}
