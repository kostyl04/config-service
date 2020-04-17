package com.kostylenko.config_service.config_provider.container;

import com.kostylenko.config_service.config_provider.model.ConfigKey;

public interface ParameterContainer<V> {

    void save(String name, V parameter);

    void delete(String name);

    boolean supports(ConfigKey configKey);

    Class<V> getParameterType();

}
