package com.kostylenko.config_service.config_service_rest.rest.service;

import com.kostylenko.config_service.config_service_rest.domain.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/applications", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class ApplicationController {

    private ApplicationService applicationService;

    @GetMapping
    public Set<String> getApplications() {
        return applicationService.getApplicationNames();
    }
}
