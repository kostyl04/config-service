package com.kostylenko.config_service.config_provider_autoconfiguration.container;

import com.kostylenko.config_service.config_provider.common_config.Message;
import com.kostylenko.config_service.config_provider.container.GenericParameterContainer;
import com.kostylenko.config_service.config_provider.container.MessageParameterContainer;
import com.kostylenko.config_service.config_provider_autoconfiguration.model.Config;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

class ParameterContainersBeanDefinitionFactory {

    @SuppressWarnings("unchecked")
    AbstractBeanDefinition createParameterContainerBeanDefinition(Config config) {
        boolean isMessage = config.getClazz().isAssignableFrom(Message.class);
        AbstractBeanDefinition beanDefinition;
        if (isMessage) {
            beanDefinition = createMessageParameterContainerBeanDefinition(config);
        } else {
            beanDefinition = createGenericParameterContainerBeanDefinition(config);
        }
        return beanDefinition;
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
}
