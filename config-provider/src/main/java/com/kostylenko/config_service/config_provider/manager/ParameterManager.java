package com.kostylenko.config_service.config_provider.manager;

import com.kostylenko.config_service.config_provider.event.CreateParameterEvent;
import com.kostylenko.config_service.config_provider.event.DeleteParameterEvent;
import com.kostylenko.config_service.config_provider.event.UpdateParameterEvent;

public interface ParameterManager {

    void processCreate(CreateParameterEvent event);

    void processUpdate(UpdateParameterEvent event);

    void processDelete(DeleteParameterEvent event);

}
