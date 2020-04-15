package com.kostylenko.config_service.config_service_rest.domain.service.parser;

import com.kostylenko.config_service.config_service_rest.domain.service.exception.TypeFieldParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.kostylenko.config_service.config_service_rest.domain.model.Type.BOOLEAN;

@Slf4j
@Component
public class BooleanTypeFieldParser extends AbstractTypeFieldParser<Boolean> {

    public BooleanTypeFieldParser() {
        super(BOOLEAN);
    }

    @Override
    public Boolean parse(Object value) throws TypeFieldParseException {
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        if (!value.toString().equalsIgnoreCase("true") &&
                !value.toString().equalsIgnoreCase("false")) {
            throw getTypeFieldParseException(value);
        }
        return Boolean.valueOf(value.toString());
    }
}
