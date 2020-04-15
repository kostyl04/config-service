package com.kostylenko.config_service.config_service_rest.rest.model;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Meta {

    @Include
    @NotBlank
    @Pattern(regexp = "\\w{3,}")
    private String name;
    @NotEmpty
    private Set<@Valid Field> fields;
    @Pattern(regexp = "[:.,_-]")
    private String keyDelimiter;
}