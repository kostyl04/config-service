package com.kostylenko.config_service.config_provider_autoconfiguration.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kostylenko.config_service.config_provider.event.ParameterEvent;
import com.kostylenko.config_service.config_provider.manager.ParameterManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
public class ParameterListener implements MessageListener {

    private ObjectMapper objectMapper;
    private ParameterManager containerParameterManager;

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            ParameterEvent parameterEvent;
            try {
                String messageText = ((TextMessage) message).getText();
                parameterEvent = objectMapper.readValue(messageText, ParameterEvent.class);
                containerParameterManager.process(parameterEvent);
            } catch (Exception e) {
                log.error("Error processing event: ", e);
                throw new RuntimeException(e);
            }
        }
    }
}
