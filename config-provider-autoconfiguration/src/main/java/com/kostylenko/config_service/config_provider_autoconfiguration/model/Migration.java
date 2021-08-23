package com.kostylenko.config_service.config_provider_autoconfiguration.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Migration {

    private boolean enabled;
    private String fileLocation;
}
