package com.kostylenko.config_service.config_service_rest.rest.service;

import com.kostylenko.config_service.config_service_rest.rest.model.Meta;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/meta", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class MetaController {

    @GetMapping(value = "/{metaName}")
    public Meta getMeta(@PathVariable String metaName) {
        return null;
    }

    @GetMapping
    public List<Meta> getMetas() {
        return null;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Meta createMeta(@Valid @RequestBody Meta meta) {
        return null;
    }

}
