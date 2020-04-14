package com.kostylenko.config_service.config_service_rest.domain.service;

import com.kostylenko.common.common_http.exception.BadRequestApiException;
import com.kostylenko.common.common_http.exception.InternalServerErrorException;
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

import static com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages.CONFIG_NOT_FOUND;
import static com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages.CORRUPTED_CONFIG;
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
        Meta meta = config.getMeta();
        if (isNull(meta)) {
            log.warn("Corrupted config {}", config);
            throw new InternalServerErrorException(CORRUPTED_CONFIG);
        }
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
        return null;
    }

    public Parameter deleteParameter(ParameterKey parameterKey) {
        return null;
    }
}