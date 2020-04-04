package com.kostylenko.config_service.config_service_rest.rest.service;

import com.kostylenko.config_service.config_service_rest.rest.model.Parameter;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/configs/{appName}/{configName}/{version}/parameters",
        consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class ParameterController {

    @PostMapping
    @ResponseStatus(CREATED)
    public Parameter create(@PathVariable String appName,
                            @PathVariable String configName,
                            @PathVariable String version,
                            @Valid @RequestBody Parameter parameter) {
        return null;
    }

    @GetMapping(value = "/{name}")
    public Parameter getByName(@PathVariable String appName,
                               @PathVariable String configName,
                               @PathVariable String version,
                               @PathVariable String name) {
        return null;
    }

    @GetMapping
    public List<Parameter> getAll(@PathVariable String appName,
                                  @PathVariable String configName,
                                  @PathVariable String version) {
        return null;
    }

    @PutMapping(value = "/{name}")
    public Parameter update(@PathVariable String appName,
                            @PathVariable String configName,
                            @PathVariable String version,
                            @PathVariable String name,
                            @Valid @RequestBody Parameter parameter) {
        return null;
    }

    @DeleteMapping(value = "/{name}")
    @ResponseStatus(NO_CONTENT)
    public Parameter delete(@PathVariable String appName,
                            @PathVariable String configName,
                            @PathVariable String version,
                            @PathVariable String name) {
        return null;
    }
}
