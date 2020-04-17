package com.kostylenko.config_service.config_provider.event;

import static com.kostylenko.config_service.config_provider.event.ParameterEvent.EventType.DELETE;

public class DeleteParameterEvent extends ParameterEvent {

    public DeleteParameterEvent() {
        super(DELETE);
    }
}
