package com.kostylenko.config_service.config_service_rest.data.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ConfigKey {

    private String appName;
    private String configName;
    private String version;

}
