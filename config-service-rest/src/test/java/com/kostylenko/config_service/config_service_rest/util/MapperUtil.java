package com.kostylenko.config_service.config_service_rest.util;

import com.kostylenko.common.common_mapper.domain.converter.BaseConverter;
import com.kostylenko.common.common_mapper.domain.mapper.DefaultMapper;
import com.kostylenko.common.common_mapper.domain.mapper.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unchecked")
@Slf4j
public class MapperUtil {

    public static final Mapper MAPPER;

    static {
        Reflections reflections = new Reflections("com.kostylenko.config_service.config_service_rest.converter");
        Set<Class<? extends BaseConverter>> subTypesOf = reflections.getSubTypesOf(BaseConverter.class);
        List<? extends BaseConverter> converters = subTypesOf.stream().map(converterClazz -> {
            try {
                Constructor<? extends BaseConverter> constructor = converterClazz.getConstructor();
                return constructor.newInstance();
            } catch (Exception e) {
                log.warn("Cannot create {}", converterClazz);
                return null;
            }
        }).filter(Objects::nonNull).collect(toList());
        MAPPER = new DefaultMapper((List<BaseConverter>) converters);
    }


}
