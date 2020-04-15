package com.kostylenko.config_service.config_service_rest.domain.model;

import com.kostylenko.common.common_http.exception.InternalServerErrorException;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

import static com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages.CORRUPTED_CONFIG;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Config {

    @Include
    private String id;
    @Include
    private ConfigKey configKey;
    private Meta meta;
    private Set<Parameter> parameters;

    public Meta getMeta() {
        if (isNull(meta)) {
            log.warn("Corrupted config {}", configKey);
            throw new InternalServerErrorException(CORRUPTED_CONFIG);
        }
        return meta;
    }

    public void addParameter(Parameter parameter) {
        if (nonNull(parameter)) {
            getParameters().add(parameter);
        }
    }

    public Set<Parameter> getParameters() {
        if (isNull(parameters)) {
            parameters = new HashSet<>();
        }
        return parameters;
    }
}
