package com.kostylenko.config_service.config_provider_autoconfiguration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ConfigLoader implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    private ConfigProviderProperties configuration;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        configuration.getConfigs().forEach(config -> {
            ParameterContainerBeanDefinitionFactory factory = new ParameterContainerBeanDefinitionFactory();
            AbstractBeanDefinition beanDefinition = factory.buildParameterContainerBeanDefinition(config);
            beanDefinitionRegistry.registerBeanDefinition(config.getBeanName(), beanDefinition);
        });
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    }

    @Override
    public void setEnvironment(Environment environment) {
        bindProperties(environment);
    }

    private void bindProperties(Environment environment) {
        this.configuration = Binder.get(environment)
                .bind("config-provider", ConfigProviderProperties.class)
                .orElseThrow(IllegalStateException::new);
    }

}
