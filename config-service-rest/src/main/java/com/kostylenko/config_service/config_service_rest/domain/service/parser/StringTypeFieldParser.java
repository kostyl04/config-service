package com.kostylenko.config_service.config_service_rest.domain.service.parser;

import org.springframework.stereotype.Component;

import static com.kostylenko.config_service.config_service_rest.domain.model.Type.STRING;

@Component
public class StringTypeFieldParser extends AbstractTypeFieldParser<String> {

    public StringTypeFieldParser() {
        super(STRING);
    }

    @Override
    public String parse(Object value) {
        return value.toString();
    }
}
