package com.kostylenko.config_service.config_service_rest.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Getter
@Setter
@EqualsAndHashCode
public class Parameter {

    @JsonProperty(access = READ_ONLY)
    private ParameterKey parameterKey;
    @Exclude
    @Valid
    private Map<String, Object> value;

}