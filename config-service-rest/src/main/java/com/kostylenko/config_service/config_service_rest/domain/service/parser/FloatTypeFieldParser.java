package com.kostylenko.config_service.config_service_rest.domain.service.parser;

import com.kostylenko.config_service.config_service_rest.domain.service.exception.TypeFieldParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.kostylenko.config_service.config_service_rest.domain.model.Type.FLOAT;
import static com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages.INVALID_PARSING_VALUE;

@Slf4j
@Component
public class FloatTypeFieldParser extends AbstractTypeFieldParser<Float> {

    public FloatTypeFieldParser() {
        super(FLOAT);
    }

    @Override
    public Float parse(Object value) throws TypeFieldParseException {
        try {
            return Float.valueOf(value.toString());
        } catch (Exception e) {
            log.warn("Invalid parsing value {}", value);
            throw new TypeFieldParseException(INVALID_PARSING_VALUE);
        }
    }
}
