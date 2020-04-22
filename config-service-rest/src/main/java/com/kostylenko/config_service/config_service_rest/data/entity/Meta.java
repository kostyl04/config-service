package com.kostylenko.config_service.config_service_rest.data.entity;

import com.kostylenko.config_service.config_service_rest.data.model.Field;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
@TypeAlias("meta")
public class Meta {

    @Id
    @Include
    private String id;
    @Include
    private String name;
    private Set<Field> fields;
    private String keyDelimiter;
}