package com.kostylenko.config_service.config_provider.container;

import com.kostylenko.config_service.config_provider.model.ConfigKey;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class GenericParameterContainer<V> implements ParameterContainer<V> {

    private final ConcurrentHashMap<String, V> parameters;
    private final ConfigKey configKey;
    private final Class<V> type;
    private final String beanName;

    @Override
    public void save(String name, V parameter) {
        parameters.put(name, parameter);
    }

    @Override
    public void delete(String name) {
        parameters.remove(name);
    }

    @Override
    public boolean supports(ConfigKey configKey) {
        return this.configKey.equals(configKey);
    }

    @Override
    public Class<V> getParameterType() {
        return type;
    }

    @Override
    public String getContainerBeanName() {
        return this.beanName;
    }

    @Override
    public Object getContainerBean() {
        return this.parameters;
    }
}