package com.kostylenko.config_service.config_service_rest.converter.parameter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kostylenko.common.common_mapper.domain.converter.BaseConverter;
import com.kostylenko.config_service.config_service_rest.domain.model.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ParameterToProviderParameterConverter extends BaseConverter<Parameter, com.kostylenko.config_service.config_provider.model.Parameter> {

    private ObjectMapper objectMapper;

    @Override
    public com.kostylenko.config_service.config_provider.model.Parameter convert(Parameter from, com.kostylenko.config_service.config_provider.model.Parameter to) {
        to.setParameterKey(objectMapper.convertValue(from.getParameterKey(), JsonNode.class));
        to.setValue(objectMapper.convertValue(from.getValue(), JsonNode.class));
        return to;
    }
}
