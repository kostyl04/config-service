package com.kostylenko.config_service.config_provider.container;

import com.kostylenko.config_service.config_provider.common_config.Message;
import com.kostylenko.config_service.config_provider.common_config.Messages;
import com.kostylenko.config_service.config_provider.model.ConfigKey;

import static java.util.Objects.isNull;

public class MessageParameterContainer extends GenericParameterContainer<Message> implements Messages {

    public MessageParameterContainer(ConfigKey configKey, String beanName) {
        super(configKey, Message.class, beanName);
    }

    @Override
    public String getMessage(String name, String lang) {
        Message message = get(name + ":" + lang);
        if (isNull(message)) {
            return null;
        }
        return message.getText();
    }
}
