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
    @Pattern(regexp = "\\w{3,}[._-]\\w+")
    private String appName;
    @NotBlank
    @Pattern(regexp = "\\w{3,}[._-]\\w+")
    private String configName;
    @NotBlank
    @Pattern(regexp = "(?!\\.)(\\d+(\\.\\d+)+)")
    private String version;

}