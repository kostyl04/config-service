package com.kostylenko.config_service.config_provider_autoconfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kostylenko.config_service.config_provider.client.ConfigServiceClient;
import com.kostylenko.config_service.config_provider.container.ParameterContainer;
import com.kostylenko.config_service.config_provider.manager.ContainerParameterManager;
import com.kostylenko.config_service.config_provider.manager.ParameterManager;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Slf4j
@Configuration
@AllArgsConstructor
@ComponentScan("com.kostylenko.config_service.config_provider_autoconfiguration")
public class ConfigProviderAutoConfiguration {

    private ConfigProviderProperties properties;

    @Bean
    @ConditionalOnMissingBean(ConfigServiceClient.class)
    public ConfigServiceClient configServiceClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(ConfigServiceClient.class))
                .logLevel(Logger.Level.FULL)
                .target(ConfigServiceClient.class, properties.getUrl());
    }

    @Bean
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    @ConditionalOnMissingBean(ParameterManager.class)
    public ParameterManager parameterManager(Set<ParameterContainer> containers) {
        return new ContainerParameterManager(containers,
                objectMapper(), configServiceClient());
    }

}


