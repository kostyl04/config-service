package com.kostylenko.config_service.config_service_rest.rest.service;

import com.kostylenko.config_service.config_service_rest.rest.model.Config;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/configs", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class ConfigController {

    @PostMapping
    @ResponseStatus(CREATED)
    public Config createConfig(@Valid @RequestBody Config config) {
        return config;
    }

}
