package com.kostylenko.config_service.config_provider.container;

import com.kostylenko.config_service.config_provider.common_config.Message;
import com.kostylenko.config_service.config_provider.common_config.MessageMap;
import com.kostylenko.config_service.config_provider.model.ConfigKey;

public class MessageParameterContainer extends GenericParameterContainer<Message> {

    private MessageMap messageMap;

    public MessageParameterContainer(MessageMap messageMap, ConfigKey configKey, String beanName) {
        super(messageMap.getMessages(), configKey, Message.class, beanName);
        this.messageMap = messageMap;
    }

    @Override
    public Object getContainerBean() {
        return messageMap;
    }
}
