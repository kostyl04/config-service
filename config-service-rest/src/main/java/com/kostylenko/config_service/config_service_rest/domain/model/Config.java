package com.kostylenko.config_service.config_service_rest.domain.model;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Config {

    @Include
    private String id;
    @Include
    private ConfigKey configKey;
    private Meta meta;
    private List<Parameter> parameters;

}
