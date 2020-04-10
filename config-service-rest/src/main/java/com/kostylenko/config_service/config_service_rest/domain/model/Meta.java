package com.kostylenko.config_service.config_service_rest.domain.model;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Meta {

    @Include
    private String name;
    private Set<Field> fields;
    private String keyDelimiter;

}