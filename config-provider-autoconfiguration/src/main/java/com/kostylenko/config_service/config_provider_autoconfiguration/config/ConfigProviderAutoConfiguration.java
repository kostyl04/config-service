package com.kostylenko.config_service.config_provider_autoconfiguration.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kostylenko.config_service.config_provider.client.ConfigServiceClient;
import com.kostylenko.config_service.config_provider.container.ParameterContainer;
import com.kostylenko.config_service.config_provider.manager.ContainerParameterManager;
import com.kostylenko.config_service.config_provider.manager.ParameterManager;
import com.kostylenko.config_service.config_provider_autoconfiguration.listener.ParameterListener;
import com.kostylenko.config_service.config_provider_autoconfiguration.model.ConfigProviderProperties;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import java.util.Set;

@Slf4j
@Configuration
@AllArgsConstructor
@ComponentScan("com.kostylenko.config_service.config_provider_autoconfiguration")
public class ConfigProviderAutoConfiguration {

    private ConfigProviderProperties properties;
    private static final String TOPIC_NAME = "config-service.parameters";

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
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @ConditionalOnMissingBean(ParameterManager.class)
    public ParameterManager parameterManager(Set<ParameterContainer> containers, ConfigServiceClient configServiceClient) {
        return new ContainerParameterManager(containers,
                objectMapper(), configServiceClient);
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(properties.getBrokerUrl());
        return connectionFactory;
    }

    @Bean
    public DefaultMessageListenerContainer parameterListenerContainer(ParameterManager parameterManager,
                                                                      ObjectMapper mapper) {
        DefaultMessageListenerContainer endpoint =
                new DefaultMessageListenerContainer();
        ParameterListener listener = new ParameterListener(mapper, parameterManager);
        endpoint.setMessageListener(listener);
        endpoint.setDestination(new ActiveMQTopic(TOPIC_NAME));
        endpoint.setConnectionFactory(connectionFactory());
        endpoint.start();
        return endpoint;
    }
}
