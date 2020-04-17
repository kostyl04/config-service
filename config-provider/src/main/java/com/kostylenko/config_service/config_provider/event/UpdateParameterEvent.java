package com.kostylenko.config_service.config_provider.event;

import static com.kostylenko.config_service.config_provider.event.ParameterEvent.EventType.UPDATE;

public class UpdateParameterEvent extends ParameterEvent {

    public UpdateParameterEvent() {
        super(UPDATE);
    }
}
