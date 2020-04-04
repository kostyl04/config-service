package com.kostylenko.config_service.config_service_rest.rest.model;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
public class Meta {

    @NotBlank
    private String name;
    @Exclude
    @NotEmpty
    private Set<@Valid Field> fields;
    @NotBlank
    private String keyDelimiter;
}