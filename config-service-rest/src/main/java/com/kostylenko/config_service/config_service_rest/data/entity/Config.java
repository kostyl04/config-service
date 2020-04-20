package com.kostylenko.config_service.config_service_rest.data.entity;

import com.kostylenko.config_service.config_service_rest.data.model.ConfigKey;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.isNull;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
@TypeAlias("config")
public class Config {

    @Id
    @Include
    private String id;
    @Include
    private ConfigKey configKey;
    @DBRef
    private Meta meta;
    @DBRef
    private Set<Parameter> parameters;

    public Set<Parameter> getParameters() {
        if (isNull(parameters)) {
            parameters = new HashSet<>();
        }
        parameters.removeIf(Objects::isNull);
        return parameters;
    }

}
