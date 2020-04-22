package com.kostylenko.config_service.config_provider.model;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Config {

    @Include
    private ConfigKey configKey;
    private Set<Parameter> parameters;
}
