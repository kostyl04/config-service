package com.kostylenko.config_service.config_provider.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Parameter {

    @Include
    private JsonNode parameterKey;

    private JsonNode value;

    public String getParameterName(){
        return parameterKey.findPath("name").asText();
    }

    public String getParameterValue(){
        return value.toString();
    }

}
