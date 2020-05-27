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

    @NotBlank(message = "configKey.appName")
    @Pattern(regexp = "\\w{3,}([._-]\\w+)*", message = "configKey.appName")
    private String appName;
    @NotBlank(message = "configKey.configName")
    @Pattern(regexp = "\\w{3,}([._-]\\w+)*", message = "configKey.configName")
    private String configName;
    @NotBlank(message = "configKey.version")
    @Pattern(regexp = "(?!\\.)(\\d+(\\.\\d+)+)", message = "configKey.version")
    private String version;
}