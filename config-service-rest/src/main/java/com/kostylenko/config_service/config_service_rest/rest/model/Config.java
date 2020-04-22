package com.kostylenko.config_service.config_service_rest.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
import static java.util.Objects.isNull;

@Getter
@Setter
@EqualsAndHashCode
public class Config {

    @Valid
    private ConfigKey configKey;
    @NotBlank
    @JsonProperty(access = WRITE_ONLY)
    private String metaName;
    @Exclude
    @JsonProperty(access = READ_ONLY)
    private Meta meta;
    @Exclude
    @JsonProperty(access = READ_ONLY)
    private Set<Parameter> parameters;

    public Set<Parameter> getParameters() {
        if (isNull(parameters)) {
            parameters = new HashSet<>();
        }
        parameters.removeIf(Objects::isNull);
        return parameters;
    }
}