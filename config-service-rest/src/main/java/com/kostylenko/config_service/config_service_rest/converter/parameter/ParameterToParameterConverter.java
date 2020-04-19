package com.kostylenko.config_service.config_service_rest.converter.parameter;

import com.kostylenko.common.common_mapper.domain.converter.BaseConverter;
import com.kostylenko.common.common_mapper.domain.converter.ConverterScope;
import com.kostylenko.config_service.config_service_rest.domain.model.Parameter;
import org.springframework.stereotype.Component;

@Component
@ConverterScope("update")
public class ParameterToParameterConverter extends BaseConverter<Parameter, Parameter> {

    @Override
    public Parameter convert(Parameter from, Parameter to) {
        to.setParameterKey(from.getParameterKey());
        to.setValue(from.getValue());
        return to;
    }
}
