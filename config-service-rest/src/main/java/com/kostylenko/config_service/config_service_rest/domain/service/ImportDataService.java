package com.kostylenko.config_service.config_service_rest.domain.service;

import com.kostylenko.common.common_mapper.domain.mapper.Mapper;
import com.kostylenko.config_service.config_service_rest.domain.model.Config;
import com.kostylenko.config_service.config_service_rest.domain.model.Meta;
import com.kostylenko.config_service.config_service_rest.domain.model.Parameter;
import com.kostylenko.config_service.config_service_rest.domain.model.ParameterKey;
import com.kostylenko.config_service.config_service_rest.domain.model.importing.ImportData;
import com.kostylenko.config_service.config_service_rest.domain.service.factory.ParameterKeyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportDataService {

    private final Mapper mapper;
    private final MetaService metaService;
    private final ConfigService configService;
    private final ParameterService parameterService;
    private final ParameterKeyFactory parameterKeyFactory;

    public void importData(ImportData importData) {
        log.info("Data importing has been started");
        importMeta(importData.getMeta());
        importConfigs(importData.getConfigs());
        log.info("Data have been successfully imported");
    }

    private void importMeta(List<Meta> metaSet) {
        metaSet.forEach(meta -> {
            if (!metaService.isMetaExist(meta.getName())) {
                Meta createdMeta = metaService.createMeta(meta);
                log.debug("Meta successfully created: {}", createdMeta.getName());
            }
        });
    }

    private void importConfigs(List<com.kostylenko.config_service.config_service_rest.domain.model.importing.Config> configs) {
        configs.forEach(importConfig -> {
            Config config = configService.getConfig(importConfig.getConfigKey());
            if (isNull(config)) {
                Config configCopy = mapper.map(importConfig, Config.class, "import");
                config = configService.createConfig(configCopy);
                log.debug("Config successfully created: {}", config.getConfigKey());
            }
            final Config createdConfig = config;
            Set<Parameter> parameters = buildParametersWithParameterKey(importConfig.getParameters(), createdConfig);
            createdConfig.setParameters(parameters);
            importParameters(createdConfig.getParameters(), createdConfig);
        });
    }

    private void importParameters(Set<Parameter> parameters, Config config) {
        parameters.forEach(parameter -> {
            Parameter savedParameter = parameterService.createOrUpdateParameter(parameter, config);
            log.debug("Parameter successfully saved: {}", savedParameter.getParameterKey());
        });
    }

    private Set<Parameter> buildParametersWithParameterKey(List<Parameter> parameters, com.kostylenko.config_service.config_service_rest.domain.model.Config config) {
        return parameters.stream()
                .map(parameter -> {
                    ParameterKey parameterKey = parameterKeyFactory.buildParameterKey(config, parameter);
                    parameter.setParameterKey(parameterKey);
                    return parameter;
                })
                .collect(toSet());
    }
}
