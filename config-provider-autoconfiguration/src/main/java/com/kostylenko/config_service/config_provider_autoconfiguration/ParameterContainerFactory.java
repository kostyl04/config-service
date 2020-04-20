package com.kostylenko.config_service.config_provider_autoconfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kostylenko.config_service.config_provider.client.ConfigServiceClient;
import com.kostylenko.config_service.config_provider.common_config.Message;
import com.kostylenko.config_service.config_provider.common_config.MessageMap;
import com.kostylenko.config_service.config_provider.container.GenericParameterContainer;
import com.kostylenko.config_service.config_provider.container.MessageParameterContainer;
import com.kostylenko.config_service.config_provider.container.ParameterContainer;
import com.kostylenko.config_service.config_provider.model.Parameter;
import com.kostylenko.config_service.config_provider_autoconfiguration.model.Config;
import lombok.AllArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
@AllArgsConstructor
public class ParameterContainerFactory {

    private ObjectMapper objectMapper;
    private ConfigurableApplicationContext context;
    private ConfigServiceClient configServiceClient;

    public ParameterContainer buildParameterContainer(Config config) {
        Set<Parameter> parameters = configServiceClient.getConfig(config.getAppName(), config.getName(), config.getVersion()).getParameters();
        ParameterContainer parameterContainer;
        if (config.getClazz().equals(Message.class)) {
            parameterContainer = buildMessageParameterContainer(parameters, config);
        } else {
            parameterContainer = buildGenericParameterContainer(parameters, config);
        }
        context.getBeanFactory().registerSingleton(parameterContainer.getContainerBeanName(), parameterContainer.getContainerBean());
        return parameterContainer;
    }

    private GenericParameterContainer buildGenericParameterContainer(Set<Parameter> parameters, Config config) {
        ConcurrentHashMap<String, Object> parameterMap = mapAndExposeParameters(parameters, config);
        return new GenericParameterContainer(parameterMap, config.getConfigKey(), config.getClazz(), config.getBeanName());
    }

    private MessageParameterContainer buildMessageParameterContainer(Set<Parameter> parameters, Config config) {
        Map<String, Message> parameterMap = (Map<String, Message>) (Map) getParameterMap(parameters, config);
        ConcurrentHashMap<String, Message> messageMap = new ConcurrentHashMap<>(parameterMap);
        MessageMap messagesBean = new MessageMap(messageMap);
        return new MessageParameterContainer(messagesBean, config.getConfigKey(), config.getBeanName());
    }

    private ConcurrentHashMap<String, Object> mapAndExposeParameters(Set<Parameter> parameters, Config config) {
        Map<String, Object> collect = getParameterMap(parameters, config);
        return new ConcurrentHashMap<>(collect);
    }

    private Map<String, Object> getParameterMap(Set<Parameter> parameters, Config config) {
        return parameters.stream().collect(Collectors.toMap(Parameter::getParameterName,
                parameter -> {
                    try {
                        return objectMapper.readValue(parameter.getParameterValue(), config.getClazz());
                    } catch (JsonProcessingException e) {
                        throw new IllegalArgumentException(String.format("Cannot parse config: %s parameter %s to class: %s",
                                config.getName(),
                                parameter.getParameterName(),
                                config.getClazz()));
                    }
                }
        ));
    }

}
