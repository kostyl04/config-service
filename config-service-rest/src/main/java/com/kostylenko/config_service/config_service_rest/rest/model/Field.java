package com.kostylenko.config_service.config_service_rest.rest.model;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static javax.validation.constraints.Pattern.Flag.CASE_INSENSITIVE;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Field {

    @Include
    @NotBlank(message = "field.name")
    @Pattern(regexp = "[a-zA-Z]+", message = "field.name")
    private String name;
    private boolean nullable = true;
    private boolean key;
    private boolean immutable;
    @NotBlank(message = "field.type")
    @Pattern(regexp = "long|string|list|map|float|boolean", message = "field.type", flags = CASE_INSENSITIVE)
    private String type;
}