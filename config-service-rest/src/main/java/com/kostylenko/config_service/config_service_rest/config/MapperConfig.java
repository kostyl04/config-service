package com.kostylenko.config_service.config_service_rest.config;

import com.kostylenko.common.common_mapper.domain.converter.BaseConverter;
import com.kostylenko.common.common_mapper.domain.mapper.DefaultMapper;
import com.kostylenko.common.common_mapper.domain.mapper.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Set;

@Configuration
public class MapperConfig {

    @Bean
    public Mapper mapper(Set<BaseConverter> converters) {
        return new DefaultMapper(new ArrayList<>(converters));
    }

}
