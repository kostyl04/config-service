package com.kostylenko.config_service.config_service_rest.data.entity;

import com.kostylenko.config_service.config_service_rest.data.model.ParameterKey;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
@Document
@TypeAlias("parameter")
public class Parameter {

    @Id
    private String id;
    private ParameterKey parameterKey;
    private Map<String, Object> value;
}
