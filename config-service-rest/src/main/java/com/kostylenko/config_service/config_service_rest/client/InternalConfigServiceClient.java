package com.kostylenko.config_service.config_service_rest.client;

import com.kostylenko.common.common_mapper.domain.mapper.Mapper;
import com.kostylenko.config_service.config_provider.client.ConfigServiceClient;
import com.kostylenko.config_service.config_provider.model.Config;
import com.kostylenko.config_service.config_provider.model.Parameter;
import com.kostylenko.config_service.config_service_rest.domain.model.ConfigKey;
import com.kostylenko.config_service.config_service_rest.domain.model.ParameterKey;
import com.kostylenko.config_service.config_service_rest.domain.service.ConfigService;
import com.kostylenko.config_service.config_service_rest.domain.service.ParameterService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InternalConfigServiceClient implements ConfigServiceClient {

    private ConfigService configService;
    private ParameterService parameterService;
    private Mapper mapper;

    @Override
    public Config getConfig(String appName, String configName, String version) {
        ConfigKey configKey = new ConfigKey();
        configKey.setAppName(appName);
        configKey.setConfigName(configName);
        configKey.setVersion(version);
        var config = configService.getConfig(configKey);
        return mapper.map(config, Config.class);
    }

    @Override
    public Parameter getParameter(String appName, String configName, String version, String name) {
        ParameterKey parameterKey = new ParameterKey();
        parameterKey.setAppName(appName);
        parameterKey.setConfigName(configName);
        parameterKey.setVersion(version);
        parameterKey.setName(name);
        var parameter = parameterService.getParameter(parameterKey);
        return mapper.map(parameter, Parameter.class);
    }
}
