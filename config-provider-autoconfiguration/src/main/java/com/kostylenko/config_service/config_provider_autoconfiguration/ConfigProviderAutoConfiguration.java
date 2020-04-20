package com.kostylenko.config_service.config_provider_autoconfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kostylenko.config_service.config_provider.client.ConfigServiceClient;
import com.kostylenko.config_service.config_provider.container.ParameterContainer;
import com.kostylenko.config_service.config_provider.manager.ContainerParameterManager;
import com.kostylenko.config_service.config_provider.manager.ParameterManager;
import com.kostylenko.config_service.config_provider_autoconfiguration.model.Config;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(ConfigProviderProperties.class)
@Order(HIGHEST_PRECEDENCE)
public class ConfigProviderAutoConfiguration {

    private ConfigProviderProperties properties;
    private ConfigurableApplicationContext context;

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
    public ParameterManager parameterManager() {
        return new ContainerParameterManager(parameterContainers(),
                objectMapper(), configServiceClient());
    }

    @Bean
    public Set<ParameterContainer> parameterContainers() {
        ParameterContainerFactory parameterContainerFactory = new ParameterContainerFactory(objectMapper(),context, configServiceClient());
        Set<ParameterContainer> containers = new HashSet<>();
        List<Config> configs = properties.getConfigs();
        configs.forEach(config -> containers.add(parameterContainerFactory.buildParameterContainer(config)));
        return containers;
    }
}


