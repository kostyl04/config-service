package com.kostylenko.config_service.config_service_rest.domain.service.factory;

import com.kostylenko.common.common_http.exception.BadRequestApiException;
import com.kostylenko.config_service.config_service_rest.domain.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages.VALUE_HAS_NO_KEY_FIELD;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;


@Slf4j
@Component
public class ParameterKeyFactory {

    public ParameterKey buildParameterKey(Config config, Parameter parameter) {
        ConfigKey configKey = config.getConfigKey();
        ParameterKey parameterKey = new ParameterKey();
        parameterKey.setAppName(configKey.getAppName());
        parameterKey.setConfigName(configKey.getConfigName());
        parameterKey.setVersion(configKey.getVersion());

        Map<String, Object> value = parameter.getValue();
        Meta meta = config.getMeta();
        List<String> keyValues = new ArrayList<>();
        List<Field> keyFields = meta.getFields().stream().filter(Field::isKey).collect(toList());
        keyFields.forEach(keyField -> {
            Object keyFieldValue = value.get(keyField.getName());
            if (isNull(keyFieldValue)) {
                log.warn("Value has no key field");
                throw new BadRequestApiException(VALUE_HAS_NO_KEY_FIELD);
            }
            keyValues.add(keyFieldValue.toString());
        });
        parameterKey.setName(keyValues.stream().collect(Collectors.joining(meta.getKeyDelimiter())));
        return parameterKey;
    }

}
