package com.kostylenko.config_service.config_provider.event;

import com.kostylenko.config_service.config_provider.model.ConfigKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ParameterEvent {

    private EventType eventType;
    private ConfigKey configKey;
    private String parameterName;

    public ParameterEvent(EventType eventType) {
        this.eventType = eventType;
    }

    public enum EventType {

        CREATE,
        UPDATE,
        DELETE
    }
}


