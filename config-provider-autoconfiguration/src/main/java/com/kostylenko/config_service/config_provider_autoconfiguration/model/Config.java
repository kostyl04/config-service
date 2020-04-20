package com.kostylenko.config_service.config_provider_autoconfiguration.model;

import com.kostylenko.config_service.config_provider.model.ConfigKey;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Config {

    private String beanName;
    private String appName;
    private String name;
    private String version;
    @Exclude
    private Class clazz;

    public ConfigKey getConfigKey(){
        ConfigKey configKey = new ConfigKey();
        configKey.setAppName(appName);
        configKey.setConfigName(name);
        configKey.setVersion(version);
        return configKey;
    }

}
