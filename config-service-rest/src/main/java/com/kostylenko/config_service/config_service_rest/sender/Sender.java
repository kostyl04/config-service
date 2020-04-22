package com.kostylenko.config_service.config_service_rest.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kostylenko.config_service.config_provider.event.ParameterEvent;
import com.kostylenko.config_service.config_provider.event.ParameterEvent.EventType;
import com.kostylenko.config_service.config_provider.model.ConfigKey;
import com.kostylenko.config_service.config_service_rest.domain.model.ParameterKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class Sender {

    private ObjectMapper objectMapper;
    private JmsTemplate jmsTemplate;

    public void sendEvent(EventType eventType, ParameterKey parameterKey) {
        ConfigKey configKey = new ConfigKey();
        configKey.setAppName(parameterKey.getAppName());
        configKey.setConfigName(parameterKey.getConfigName());
        configKey.setVersion(parameterKey.getVersion());
        ParameterEvent parameterEvent = new ParameterEvent(eventType);
        parameterEvent.setConfigKey(configKey);
        parameterEvent.setParameterName(parameterKey.getName());
        String jsonSendMessage;
        try {
            jsonSendMessage = objectMapper.writeValueAsString(parameterEvent);
            jmsTemplate.convertAndSend(jsonSendMessage);
        } catch (JsonProcessingException e) {
            log.warn("Cannot parse {} to json format", parameterEvent);
        }
    }
}
