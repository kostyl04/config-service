package com.kostylenko.config_service.config_provider.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kostylenko.config_service.config_provider.client.ConfigServiceClient;
import com.kostylenko.config_service.config_provider.container.ParameterContainer;
import com.kostylenko.config_service.config_provider.event.CreateParameterEvent;
import com.kostylenko.config_service.config_provider.event.DeleteParameterEvent;
import com.kostylenko.config_service.config_provider.event.ParameterEvent;
import com.kostylenko.config_service.config_provider.event.UpdateParameterEvent;
import com.kostylenko.config_service.config_provider.model.ConfigKey;
import com.kostylenko.config_service.config_provider.model.Parameter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@SuppressWarnings("unchecked")
@Slf4j
@AllArgsConstructor
public class ContainerParameterManager implements ParameterManager {

    private Set<ParameterContainer> containers;
    private ObjectMapper objectMapper;
    private ConfigServiceClient client;

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
            Object mappedParameterValue;
            try {
                mappedParameterValue = objectMapper.readValue(parameter.getParameterValue(), container.getParameterType());
            } catch (JsonProcessingException e) {
                log.error("cannot parse parameter: {} to {}, cause: ", parameter.getParameterName(), container.getParameterType(), e);
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
            Object mappedParameterValue;
            try {
                mappedParameterValue = objectMapper.readValue(parameter.getParameterValue(), container.getParameterType());
            } catch (JsonProcessingException e) {
                log.error("cannot parse parameter: {} to {}, cause: ", parameter.getParameterName(), container.getParameterType(), e);
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

    public ParameterContainer getContainer(ParameterEvent event) {
        if (isNull(containers)) {
            containers = new HashSet<>();
        }
        return containers.stream()
                .filter(container -> container.supports(event.getConfigKey()))
                .findFirst().orElse(null);
    }
}
