package com.kostylenko.config_service.config_provider_autoconfiguration.container;

import com.kostylenko.config_service.config_provider.common_config.Message;
import com.kostylenko.config_service.config_provider.common_config.Property;
import com.kostylenko.config_service.config_provider.container.GenericParameterContainer;
import com.kostylenko.config_service.config_provider.container.MessageParameterContainer;
import com.kostylenko.config_service.config_provider.container.PropertyParameterContainer;
import com.kostylenko.config_service.config_provider_autoconfiguration.model.Config;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.util.Objects.isNull;

class ParameterContainersBeanDefinitionFactory {

    private final Map<Class<?>, Function<Config, AbstractBeanDefinition>> parameterContainerBeanDefinitionCreators;

    ParameterContainersBeanDefinitionFactory() {
        parameterContainerBeanDefinitionCreators = new HashMap<>();
        parameterContainerBeanDefinitionCreators.put(Message.class, this::createMessageParameterContainerBeanDefinition);
        parameterContainerBeanDefinitionCreators.put(Property.class, this::createPropertyParameterContainerBeanDefinition);
    }

    AbstractBeanDefinition createParameterContainerBeanDefinition(Config config) {
        Class type = config.getClazz();
        Function<Config, AbstractBeanDefinition> function = parameterContainerBeanDefinitionCreators.get(type);
        return isNull(function) ? createGenericParameterContainerBeanDefinition(config) : function.apply(config);
    }

    private AbstractBeanDefinition createGenericParameterContainerBeanDefinition(Config config) {
        return BeanDefinitionBuilder.genericBeanDefinition(GenericParameterContainer.class)
                .addConstructorArgValue(config.getConfigKey())
                .addConstructorArgValue(config.getClazz())
                .addConstructorArgValue(config.getBeanName())
                .getBeanDefinition();
    }

    private AbstractBeanDefinition createMessageParameterContainerBeanDefinition(Config config) {
        return BeanDefinitionBuilder.genericBeanDefinition(MessageParameterContainer.class)
                .addConstructorArgValue(config.getConfigKey())
                .addConstructorArgValue(config.getBeanName())
                .getBeanDefinition();
    }

    private AbstractBeanDefinition createPropertyParameterContainerBeanDefinition(Config config) {
        return BeanDefinitionBuilder.genericBeanDefinition(PropertyParameterContainer.class)
                .addConstructorArgValue(config.getConfigKey())
                .addConstructorArgValue(config.getBeanName())
                .getBeanDefinition();
    }
}
