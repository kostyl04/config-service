package com.kostylenko.config_service.config_service_rest.domain.service;

import com.kostylenko.common.common_http.exception.BadRequestApiException;
import com.kostylenko.common.common_mapper.domain.mapper.Mapper;
import com.kostylenko.config_service.config_service_rest.data.repository.ParameterRepository;
import com.kostylenko.config_service.config_service_rest.domain.model.*;
import com.kostylenko.config_service.config_service_rest.domain.service.factory.ParameterKeyFactory;
import com.kostylenko.config_service.config_service_rest.domain.service.parser.FieldParser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages.*;
import static java.util.Objects.isNull;

@Slf4j
@Service
@AllArgsConstructor
public class ParameterService {

    private Mapper mapper;
    private FieldParser fieldParser;
    private ConfigService configService;
    private ParameterRepository parameterRepository;
    private ParameterKeyFactory parameterKeyFactory;

    @Transactional
    public Parameter createParameter(ConfigKey configKey, Parameter parameter) {
        Config config = configService.getConfig(configKey);
        if (isNull(config)) {
            log.warn("Config not found {}", configKey);
            throw new BadRequestApiException(CONFIG_NOT_FOUND);
        }
        ParameterKey parameterKey = parameterKeyFactory.buildParameterKey(config, parameter);
        parameter.setParameterKey(parameterKey);
        var dataParameterKey = mapper.map(parameterKey, com.kostylenko.config_service.config_service_rest.data.model.ParameterKey.class);
        boolean exists = parameterRepository.existsByParameterKey(dataParameterKey);
        if (exists) {
            log.warn("parameter already exists {}", dataParameterKey);
            throw new BadRequestApiException(PARAMETER_ALREADY_EXISTS);
        }
        Meta meta = config.getMeta();
        Map<String, Object> parse = fieldParser.parse(meta, parameter.getValue());
        parameter.setValue(parse);
        var savedParameter = parameterRepository.save(mapper.map(parameter, com.kostylenko.config_service.config_service_rest.data.entity.Parameter.class));
        Parameter newParameter = mapper.map(savedParameter, Parameter.class);
        config.addParameter(newParameter);
        configService.updateConfig(config);
        return newParameter;
    }

    public Parameter getParameter(ParameterKey parameterKey) {
        return null;
    }

    public List<Parameter> getParameters() {
        return null;
    }

    public Parameter updateParameter(Parameter parameter) {
        var dataParameterKey = mapper.map(parameter.getParameterKey(), com.kostylenko.config_service.config_service_rest.data.model.ParameterKey.class);
        Parameter oldParameter = mapper.map(parameterRepository.findByParameterKey(dataParameterKey), Parameter.class);
        if (isNull(oldParameter)) {
            log.warn("parameter {} doesn't exists", parameter.getParameterKey());
            throw new BadRequestApiException(PARAMETER_DOES_NOT_EXISTS);
        }
        Map<String, Object> oldParameterValue = oldParameter.getValue();
        ConfigKey configKey = mapper.map(oldParameter.getParameterKey(), ConfigKey.class);
        Config config = configService.getConfig(configKey);
        Meta meta = config.getMeta();
        meta.getFields().forEach(field -> {
            if (field.isKey()) {
                String fieldName = field.getName();
                parameter.getValue().put(fieldName, oldParameterValue.get(fieldName));
            }
        });
        fieldParser.parse(meta, parameter.getValue());
        Parameter parameterToSave = mapper.map(parameter, oldParameter, "update");
        var dataParameter = mapper.map(parameterToSave, com.kostylenko.config_service.config_service_rest.data.entity.Parameter.class);
        var savedParameter = parameterRepository.save(dataParameter);
        return mapper.map(savedParameter, Parameter.class);
    }

    public Parameter deleteParameter(ParameterKey parameterKey) {
        return null;
    }
}