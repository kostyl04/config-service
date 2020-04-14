package com.kostylenko.config_service.config_service_rest.domain.service.parser;

import com.kostylenko.config_service.config_service_rest.domain.service.exception.TypeFieldParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.kostylenko.config_service.config_service_rest.domain.model.Type.MAP;
import static com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages.INVALID_PARSING_VALUE;

@Slf4j
@Component
public class MapTypeFieldParser extends AbstractTypeFieldParser<Map<String, String>> {

    public MapTypeFieldParser() {
        super(MAP);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, String> parse(Object value) throws TypeFieldParseException {
        if (!(value instanceof Map)) {
            log.warn("Invalid parsing value {}", value);
            throw new TypeFieldParseException(INVALID_PARSING_VALUE);
        }
        Map tempMap = (Map) value;
        Map<String, String> result = new HashMap<>();
        tempMap.forEach((key, value1) -> result.put(key.toString(), value1.toString()));
        return result;
    }
}
