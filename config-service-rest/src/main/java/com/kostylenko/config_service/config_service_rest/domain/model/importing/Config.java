package com.kostylenko.config_service.config_service_rest.domain.model.importing;

import com.kostylenko.config_service.config_service_rest.domain.model.ConfigKey;
import com.kostylenko.config_service.config_service_rest.domain.model.Meta;
import com.kostylenko.config_service.config_service_rest.domain.model.Parameter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Config {

    @EqualsAndHashCode.Include
    private String id;
    @EqualsAndHashCode.Include
    private ConfigKey configKey;
    private Meta meta;
    private List<Parameter> parameters;
}
