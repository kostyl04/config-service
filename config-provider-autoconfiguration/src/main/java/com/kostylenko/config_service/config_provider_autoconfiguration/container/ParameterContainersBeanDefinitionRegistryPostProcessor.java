package com.kostylenko.config_service.config_provider_autoconfiguration.container;

import com.kostylenko.config_service.config_provider_autoconfiguration.model.ConfigProviderProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;

@Configuration
@SuppressWarnings("SpringFacetCodeInspection")
public class ParameterContainersBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    private ConfigProviderProperties configuration;
    private static final String CONFIG_PROVIDER_PROPERTIES_PREFIX = "config-provider";


    @Override
    public void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        configuration.getConfigs().forEach(config -> {
            ParameterContainersBeanDefinitionFactory factory = new ParameterContainersBeanDefinitionFactory();
            AbstractBeanDefinition beanDefinition = factory.createParameterContainerBeanDefinition(config);
            beanDefinitionRegistry.registerBeanDefinition(config.getBeanName(), beanDefinition);
        });
    }

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    }

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        bindProperties(environment);
    }

    private void bindProperties(Environment environment) {
        this.configuration = Binder.get(environment)
                .bind(CONFIG_PROVIDER_PROPERTIES_PREFIX, ConfigProviderProperties.class)
                .orElseThrow(IllegalStateException::new);
    }
}
