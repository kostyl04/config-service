package com.kostylenko.config_service.config_service_rest.domain.service.validator;

import com.kostylenko.config_service.config_service_rest.domain.model.Meta;
import com.kostylenko.config_service.config_service_rest.domain.model.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class MetaValidatorManager {

    private Set<CommonParameterValidator> validators;

    public void validate(Parameter parameter, Meta meta){
        validators.stream()
                .filter(validator -> validator.supports(meta))
                .findFirst().ifPresent(validator -> validator.validate(parameter));
    }
}
