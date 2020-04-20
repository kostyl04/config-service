package com.kostylenko.config_service.config_provider.container;

import com.kostylenko.config_service.config_provider.model.ConfigKey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

@Getter
@RequiredArgsConstructor
public class GenericParameterContainer<V> extends ConcurrentHashMap<String, V> implements ParameterContainer<V> {

    private final ConfigKey configKey;
    private final Class<V> type;
    private final String beanName;

    @Override
    public V get(String name) {
        return super.get(name);
    }

    @Override
    public void save(String name, V parameter) {
        put(name, parameter);
    }

    @Override
    public void delete(String name) {
        remove(name);
    }

    @Override
    public boolean supports(ConfigKey configKey) {
        return this.configKey.equals(configKey);
    }

    @Override
    public Class<V> getParameterType() {
        return type;
    }

}