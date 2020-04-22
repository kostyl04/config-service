package com.kostylenko.config_service.config_provider.manager;

import com.kostylenko.config_service.config_provider.event.ParameterEvent;

public interface ParameterManager {

    void process(ParameterEvent event);
}
