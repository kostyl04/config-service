package com.kostylenko.config_service.config_service_rest.domain.service.parser;

import com.kostylenko.config_service.config_service_rest.domain.service.exception.TypeFieldParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.kostylenko.config_service.config_service_rest.domain.model.Type.BOOLEAN;
import static com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages.INVALID_PARSING_VALUE;

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
            log.warn("Invalid parsing value {}", value);
            throw new TypeFieldParseException(INVALID_PARSING_VALUE);
        }
        return Boolean.valueOf(value.toString());
    }
}
