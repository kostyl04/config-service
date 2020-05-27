package com.kostylenko.config_service.config_service_rest.config;

import com.kostylenko.common.common_mapper.domain.mapper.Mapper;
import com.kostylenko.config_service.config_provider.client.ConfigServiceClient;
import com.kostylenko.config_service.config_service_rest.client.InternalConfigServiceClient;
import com.kostylenko.config_service.config_service_rest.domain.service.ConfigService;
import com.kostylenko.config_service.config_service_rest.domain.service.ParameterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InternalConfigProviderAutoconfiguration {

    private Mapper mapper;
    private ConfigService configService;
    private ParameterService parameterService;

    public InternalConfigProviderAutoconfiguration(Mapper mapper, ConfigService configService, ParameterService parameterService) {
        this.mapper = mapper;
        this.configService = configService;
        this.parameterService = parameterService;
    }

    @Bean
    public ConfigServiceClient configServiceClient() {
        return new InternalConfigServiceClient(configService, parameterService, mapper);
    }
}
