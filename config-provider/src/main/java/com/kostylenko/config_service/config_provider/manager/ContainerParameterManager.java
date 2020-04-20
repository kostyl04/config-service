package com.kostylenko.config_service.config_provider.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kostylenko.config_service.config_provider.client.ConfigServiceClient;
import com.kostylenko.config_service.config_provider.container.ParameterContainer;
import com.kostylenko.config_service.config_provider.event.CreateParameterEvent;
import com.kostylenko.config_service.config_provider.event.DeleteParameterEvent;
import com.kostylenko.config_service.config_provider.event.ParameterEvent;
import com.kostylenko.config_service.config_provider.event.UpdateParameterEvent;
import com.kostylenko.config_service.config_provider.model.Config;
import com.kostylenko.config_service.config_provider.model.ConfigKey;
import com.kostylenko.config_service.config_provider.model.Parameter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@SuppressWarnings("unchecked")
public class ContainerParameterManager implements ParameterManager {

    private Set<ParameterContainer> containers;
    private ObjectMapper objectMapper;
    private ConfigServiceClient client;

    public ContainerParameterManager(Set<ParameterContainer> containers, ObjectMapper objectMapper, ConfigServiceClient client) {
        this.containers = containers;
        this.objectMapper = objectMapper;
        this.client = client;
        initContainers();
    }

    private void initContainers() {
        containers.forEach(container -> {
            ConfigKey configKey = container.getConfigKey();
            Config config = client.getConfig(configKey.getAppName(),
                    configKey.getConfigName(),
                    configKey.getVersion());
            config.getParameters().forEach(parameter -> {
                Object mappedParameterValue = mapParameterValue(parameter, container, true);
                container.save(parameter.getParameterName(), mappedParameterValue);
            });
        });
    }

    @Override
    public void processCreate(CreateParameterEvent event) {
        ParameterContainer container = getContainer(event);
        if (nonNull(container)) {
            ConfigKey configKey = event.getConfigKey();
            Parameter parameter = client.getParameter(
                    configKey.getAppName(),
                    configKey.getConfigName(),
                    configKey.getVersion(),
                    event.getParameterName());
            Object mappedParameterValue = mapParameterValue(parameter, container, false);
            if (isNull(mappedParameterValue)) {
                return;
            }
            container.save(parameter.getParameterName(), mappedParameterValue);
            log.info("configKey: {}, parameter: {} created", configKey, parameter.getParameterName());
        }
    }

    @Override
    public void processUpdate(UpdateParameterEvent event) {
        ParameterContainer container = getContainer(event);
        if (nonNull(container)) {
            ConfigKey configKey = event.getConfigKey();
            Parameter parameter = client.getParameter(
                    configKey.getAppName(),
                    configKey.getConfigName(),
                    configKey.getVersion(),
                    event.getParameterName());
            Object mappedParameterValue = mapParameterValue(parameter, container, false);
            if (isNull(mappedParameterValue)) {
                return;
            }
            container.save(parameter.getParameterName(), mappedParameterValue);
            log.info("configKey: {}, parameter: {} updated", configKey, parameter.getParameterName());
        }
    }

    @Override
    public void processDelete(DeleteParameterEvent event) {
        ParameterContainer container = getContainer(event);
        if (nonNull(container)) {
            container.delete(event.getParameterName());
            log.info("configKey: {}, parameter: {} deleted", event.getConfigKey(), event.getParameterName());
        }
    }

    private ParameterContainer getContainer(ParameterEvent event) {
        if (isNull(containers)) {
            containers = new HashSet<>();
        }
        return containers.stream()
                .filter(container -> container.supports(event.getConfigKey()))
                .findFirst().orElse(null);
    }

    private Object mapParameterValue(Parameter parameter, ParameterContainer container, boolean throwException) {
        Object mappedParameter;
        try {
            mappedParameter = objectMapper.readValue(parameter.getParameterValue(), container.getParameterType());
        } catch (JsonProcessingException e) {
            if (throwException) {
                log.error("cannot parse parameter: {} to {}, cause: ", parameter.getParameterName(), container.getParameterType(), e);
                throw new IllegalArgumentException(e.getMessage());
            }
            log.error("cannot parse parameter: {} to {}, cause: ", parameter.getParameterName(), container.getParameterType(), e);
            return null;
        }
        return mappedParameter;
    }
}
