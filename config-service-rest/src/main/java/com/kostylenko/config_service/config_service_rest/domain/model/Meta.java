package com.kostylenko.config_service.config_service_rest.domain.model;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Meta {

    @Include
    private String id;
    @Include
    private String name;
    private Set<Field> fields;
    private String keyDelimiter;

    public String getKeyDelimiter() {
        if (isEmpty(keyDelimiter)) {
            keyDelimiter = ":";
        }
        return keyDelimiter;
    }

}