package com.kostylenko.config_service.config_provider.event;

import static com.kostylenko.config_service.config_provider.event.ParameterEvent.EventType.CREATE;

public class CreateParameterEvent extends ParameterEvent {

    public CreateParameterEvent() {
        super(CREATE);
    }
}
