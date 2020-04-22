package com.kostylenko.config_service.config_provider_autoconfiguration.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties("config-provider")
public class ConfigProviderProperties {

    private String url;
    private String brokerUrl;
    private String appName;
    private List<Config> configs;

    public List<Config> getConfigs() {
        configs.forEach(config -> config.setAppName(appName));
        return configs;
    }
}
