package com.kostylenko.config_service.config_service_rest.data.model;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Field {

    @Include
    private String name;
    private boolean nullable = true;
    private boolean key;
    private Type type;

}
