package com.kostylenko.config_service.config_provider.event;

import com.kostylenko.config_service.config_provider.model.ConfigKey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class ParameterEvent {

    private final EventType eventType;
    private ConfigKey configKey;
    private String parameterName;

    public enum EventType {

        CREATE,
        UPDATE,
        DELETE
    }
}


