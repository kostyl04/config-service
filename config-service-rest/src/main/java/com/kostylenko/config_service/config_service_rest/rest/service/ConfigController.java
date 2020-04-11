package com.kostylenko.config_service.config_service_rest.rest.service;

import com.kostylenko.common.common_mapper.domain.mapper.Mapper;
import com.kostylenko.config_service.config_service_rest.domain.service.ConfigService;
import com.kostylenko.config_service.config_service_rest.rest.model.Config;
import com.kostylenko.config_service.config_service_rest.rest.model.ConfigKey;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SuppressWarnings("MVCPathVariableInspection")
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/configs", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class ConfigController {

    private ConfigService configService;
    private Mapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    public Config createConfig(@Valid @RequestBody Config config) {
        var savedConfig = configService.createConfig(mapper.map(config, com.kostylenko.config_service.config_service_rest.domain.model.Config.class));
        return mapper.map(savedConfig, Config.class);
    }

    @GetMapping("/{appName}/{configName}/{version}")
    public Config getConfig(ConfigKey configKey) {
        var domainConfigKey = mapper.map(configKey, com.kostylenko.config_service.config_service_rest.domain.model.ConfigKey.class);
        return mapper.map(configService.getConfig(domainConfigKey), Config.class);
    }
}