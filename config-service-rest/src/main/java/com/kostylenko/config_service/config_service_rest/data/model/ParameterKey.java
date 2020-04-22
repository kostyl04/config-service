package com.kostylenko.config_service.config_service_rest.data.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ParameterKey extends ConfigKey {

    private String name;
}
