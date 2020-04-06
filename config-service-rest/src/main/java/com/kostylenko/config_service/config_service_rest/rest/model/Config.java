package com.kostylenko.config_service.config_service_rest.rest.model;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Config {

    @Valid
    private ConfigKey configKey;
    @NotBlank
    private String metaName;
    @Exclude
    private List<@Valid Parameter> parameters;

}
