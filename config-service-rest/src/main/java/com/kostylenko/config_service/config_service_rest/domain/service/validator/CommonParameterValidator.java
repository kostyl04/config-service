package com.kostylenko.config_service.config_service_rest.domain.service.validator;

import com.kostylenko.config_service.config_service_rest.domain.model.Meta;
import com.kostylenko.config_service.config_service_rest.domain.model.Parameter;

public interface CommonParameterValidator<M> {

    void validate(Parameter parameter);

    boolean supports(Meta meta);
}
