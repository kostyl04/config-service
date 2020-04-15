package com.kostylenko.config_service.config_service_rest.domain.service.parser;

import com.kostylenko.common.common_http.exception.BadRequestApiException;
import com.kostylenko.config_service.config_service_rest.domain.model.Field;
import com.kostylenko.config_service.config_service_rest.domain.model.Meta;
import com.kostylenko.config_service.config_service_rest.domain.service.exception.TypeFieldParseException;
import com.kostylenko.config_service.config_service_rest.domain.service.validator.NullableFieldValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages.VALUE_CAN_NOT_BE_NULL;
import static java.util.Objects.isNull;

@Slf4j
@Component
@AllArgsConstructor
public class FieldParser {

    private NullableFieldValidator nullableFieldValidator;
    private Set<TypeFieldValidatableParser> parsers;
    private final NullValueParser nullValueParser = new NullValueParser();

    public Map<String, Object> parse(Meta meta, Map<String, Object> value) {
        Map<String, Object> result = new HashMap<>();
        Set<Field> fields = meta.getFields();
        fields.forEach(field -> {
            Object objectValue = value.get(field.getName());
            if (!nullableFieldValidator.validate(field, objectValue)) {
                log.warn("This value shouldn't be null {}", objectValue);
                throw new BadRequestApiException(VALUE_CAN_NOT_BE_NULL);
            }
            TypeFieldValidatableParser parser = findParser(field, objectValue);
            try {
                result.put(field.getName(), parser.parse(objectValue));
            } catch (TypeFieldParseException e) {
                log.warn("value {} cannot be parse to " + field.getType(), objectValue);
                throw new BadRequestApiException(e.getMessage());
                //TODO when we extend common with exception arguments logic, to implement it here
            }
        });
        return result;
    }

    private TypeFieldValidatableParser findParser(Field field, Object value) {
        if (isNull(parsers)) {
            parsers = new HashSet<>();
        }
        return parsers.stream().filter(parser -> parser.accepts(field, value)).findFirst().orElse(nullValueParser);
    }

    private class NullValueParser implements TypeFieldValidatableParser {

        @Override
        public Object parse(Object value) {
            return null;
        }

        @Override
        public boolean accepts(Field field, Object value) {
            return true;
        }
    }
}
