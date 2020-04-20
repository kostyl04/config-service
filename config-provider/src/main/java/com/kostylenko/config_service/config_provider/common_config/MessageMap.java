package com.kostylenko.config_service.config_provider.common_config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.isNull;

@Getter
@AllArgsConstructor
public class MessageMap implements Messages {

    private ConcurrentHashMap<String, Message> messages;

    @Override
    public String getMessage(String name, String lang) {
        Message message = messages.get(name + ":" + lang);
        return isNull(message) ? null : message.getText();
    }
}
