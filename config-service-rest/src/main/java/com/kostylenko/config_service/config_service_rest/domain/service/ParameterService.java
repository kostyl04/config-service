package com.kostylenko.config_service.config_service_rest.domain.service;

import com.kostylenko.common.common_http.exception.BadRequestApiException;
import com.kostylenko.common.common_mapper.domain.mapper.Mapper;
import com.kostylenko.config_service.config_service_rest.data.repository.ParameterRepository;
import com.kostylenko.config_service.config_service_rest.domain.model.*;
import com.kostylenko.config_service.config_service_rest.domain.service.factory.ParameterKeyFactory;
import com.kostylenko.config_service.config_service_rest.domain.service.parser.FieldParser;
import com.kostylenko.config_service.config_service_rest.domain.service.validator.MetaValidatorManager;
import com.kostylenko.config_service.config_service_rest.sender.Sender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;
import java.util.Set;

import static com.kostylenko.config_service.config_provider.event.ParameterEvent.EventType.*;
import static com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Slf4j
@Service
@AllArgsConstructor
public class ParameterService {

    private Mapper mapper;
    private Sender sender;
    private FieldParser fieldParser;
    private ConfigService configService;
    private ParameterRepository parameterRepository;
    private ParameterKeyFactory parameterKeyFactory;
    private MetaValidatorManager metaValidatorManager;
    private TransactionTemplate transactionTemplate;

    public Parameter createParameter(ConfigKey configKey, Parameter parameter) {
        Parameter createdParameter = transactionTemplate.execute(transactionStatus -> {
            try {
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
                    log.warn("Parameter already exists {}", dataParameterKey);
                    throw new BadRequestApiException(PARAMETER_ALREADY_EXISTS);
                }
                Meta meta = config.getMeta();
                Map<String, Object> parse = fieldParser.parse(meta, parameter.getValue());
                parameter.setValue(parse);
                metaValidatorManager.validate(parameter, meta);
                var savedParameter = parameterRepository.save(mapper.map(parameter, com.kostylenko.config_service.config_service_rest.data.entity.Parameter.class));
                Parameter newParameter = mapper.map(savedParameter, Parameter.class);
                config.addParameter(newParameter);
                configService.updateConfig(config);
                return newParameter;
            } catch (Exception ex) {
                transactionStatus.setRollbackOnly();
                log.warn("Error while creating parameter: {}", ex);
                throw ex;
            }
        });
        if (nonNull(createdParameter)) {
            sender.sendEvent(CREATE, createdParameter.getParameterKey());
        }
        return createdParameter;
    }

    public Parameter getParameter(ParameterKey parameterKey) {
        var dataParameterKey = mapper.map(parameterKey, com.kostylenko.config_service.config_service_rest.data.model.ParameterKey.class);
        Parameter receivedParameter = mapper.map(parameterRepository.findByParameterKey(dataParameterKey), Parameter.class);
        if (isNull(receivedParameter)) {
            throw new BadRequestApiException(PARAMETER_DOES_NOT_EXISTS);
        }
        return receivedParameter;
    }

    public Set<Parameter> getParameters(ConfigKey configKey) {
        Config config = configService.getConfig(configKey);
        Set<Parameter> parameters = config.getParameters();
        if (isEmpty(parameters)) {
            log.warn("Parameters not found: {}", config);
            throw new BadRequestApiException(PARAMETERS_NOT_FOUND);
        }
        return parameters;
    }

    public Parameter updateParameter(Parameter parameter) {
        Parameter updatedParameter = transactionTemplate.execute(transactionStatus -> {
            try {
                var dataParameterKey = mapper.map(parameter.getParameterKey(), com.kostylenko.config_service.config_service_rest.data.model.ParameterKey.class);
                Parameter oldParameter = mapper.map(parameterRepository.findByParameterKey(dataParameterKey), Parameter.class);
                if (isNull(oldParameter)) {
                    log.warn("Parameter {} doesn't exists", parameter.getParameterKey());
                    throw new BadRequestApiException(PARAMETER_DOES_NOT_EXISTS);
                }
                Map<String, Object> oldParameterValue = oldParameter.getValue();
                ConfigKey configKey = mapper.map(oldParameter.getParameterKey(), ConfigKey.class);
                Config config = configService.getConfig(configKey);
                Meta meta = config.getMeta();
                meta.getFields().forEach(field -> {
                    if (field.isKey() || field.isImmutable()) {
                        String fieldName = field.getName();
                        parameter.getValue().put(fieldName, oldParameterValue.get(fieldName));
                    }
                });
                Map<String, Object> parse = fieldParser.parse(meta, parameter.getValue());
                parameter.setValue(parse);
                metaValidatorManager.validate(parameter, meta);
                Parameter parameterToSave = mapper.map(parameter, oldParameter, "update");
                var dataParameter = mapper.map(parameterToSave, com.kostylenko.config_service.config_service_rest.data.entity.Parameter.class);
                return mapper.map(parameterRepository.save(dataParameter), Parameter.class);
            } catch (Exception ex) {
                transactionStatus.setRollbackOnly();
                log.warn("Error while updating parameter: {}", ex);
                throw ex;
            }
        });
        if (nonNull(updatedParameter)) {
            sender.sendEvent(UPDATE, updatedParameter.getParameterKey());
        }
        return updatedParameter;
    }

    public void deleteParameter(ParameterKey parameterKey) {
        var dataParameterKey = mapper.map(parameterKey, com.kostylenko.config_service.config_service_rest.data.model.ParameterKey.class);
        parameterRepository.deleteByParameterKey(dataParameterKey);
        sender.sendEvent(DELETE, mapper.map(dataParameterKey, ParameterKey.class));
    }
}