package com.kostylenko.config_service.config_service_rest.domain.service.validator;

import com.kostylenko.common.common_http.exception.BadRequestApiException;
import com.kostylenko.config_service.config_provider.common_config.Property;
import com.kostylenko.config_service.config_service_rest.domain.model.Meta;
import com.kostylenko.config_service.config_service_rest.domain.model.Parameter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages.VALUE_IS_NOT_VALID;
import static java.util.Objects.nonNull;

@Component
public class PropertyParameterValidator implements CommonParameterValidator<Property> {

    private final Map<String, Consumer<String>> typeValidators;

    public PropertyParameterValidator() {
        typeValidators = new HashMap<>();
        typeValidators.put("map", this::validateMap);
        typeValidators.put("list", this::validateList);
        typeValidators.put("float", this::validateFloat);
        typeValidators.put("long", this::validateLong);
        typeValidators.put("boolean", this::validateBoolean);
    }

    @Override
    public void validate(Parameter parameter) {
        String type = (String) parameter.getValue().get("type");
        String value = (String) parameter.getValue().get("value");
        Consumer<String> stringConsumer = typeValidators.get(type);
        if (nonNull(stringConsumer)) {
            stringConsumer.accept(value);
        } else throw new BadRequestApiException("unknown.type");
    }

    @Override
    public boolean supports(Meta meta) {
        return Property.class.getSimpleName().equalsIgnoreCase(meta.getName());
    }

    private void validateBoolean(String value) {
        boolean matches = value.matches("(true)|(false)");
        if (!matches) {
            throw new BadRequestApiException(VALUE_IS_NOT_VALID);
        }
    }

    private void validateLong(String value) {
        try {
            Long.valueOf(value);
        } catch (Exception e) {
            throw new BadRequestApiException(VALUE_IS_NOT_VALID);
        }
    }

    private void validateFloat(String value) {
        try {
            Float.valueOf(value);
        } catch (Exception e) {
            throw new BadRequestApiException(VALUE_IS_NOT_VALID);
        }
    }


    private void validateMap(String value) {
        boolean matches = value.matches("^[{]((\\w+=\\w+)([,]\\s?\\w+=\\w+)*)*[}]$");
        if (!matches) {
            throw new BadRequestApiException(VALUE_IS_NOT_VALID);
        }
    }

    private void validateList(String value) {
        boolean matches = value.matches("^[{]((\\w+)([,]\\s?\\w+)*)*[}]$");
        if (!matches) {
            throw new BadRequestApiException(VALUE_IS_NOT_VALID);
        }
    }
}
