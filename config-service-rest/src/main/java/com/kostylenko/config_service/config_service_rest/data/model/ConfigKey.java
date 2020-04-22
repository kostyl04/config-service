package com.kostylenko.config_service.config_service_rest.data.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ConfigKey {

    private String appName;
    private String configName;
    private String version;
}
