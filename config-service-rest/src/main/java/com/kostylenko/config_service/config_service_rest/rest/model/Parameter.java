package com.kostylenko.config_service.config_service_rest.rest.model;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
@EqualsAndHashCode
public class Parameter {

    @Valid
    private ParameterKey parameterKey;
    @Exclude
    @Valid
    private Object value;

}
