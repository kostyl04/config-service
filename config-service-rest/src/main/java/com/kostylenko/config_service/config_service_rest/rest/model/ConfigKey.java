package com.kostylenko.config_service.config_service_rest.rest.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@EqualsAndHashCode
public class ConfigKey {

    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String appName;
    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String configName;
    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String version;

}