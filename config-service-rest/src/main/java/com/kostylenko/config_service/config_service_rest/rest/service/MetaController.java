package com.kostylenko.config_service.config_service_rest.rest.service;

import com.kostylenko.common.common_mapper.domain.mapper.Mapper;
import com.kostylenko.config_service.config_service_rest.domain.service.MetaService;
import com.kostylenko.config_service.config_service_rest.rest.model.Meta;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/meta", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class MetaController {

    private Mapper mapper;
    private MetaService metaService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Meta createMeta(@Valid @RequestBody Meta meta) {
        var domainMeta = mapper.map(meta, com.kostylenko.config_service.config_service_rest.domain.model.Meta.class);
        var savedMeta = metaService.createMeta(domainMeta);
        return mapper.map(savedMeta, Meta.class);
    }

    @GetMapping(value = "/{metaName}")
    public Meta getMeta(@PathVariable String metaName) {
        var meta = metaService.getMeta(metaName);
        return mapper.map(meta, Meta.class);
    }

    @GetMapping
    public List<Meta> getMetas() {
        var metas = metaService.getMetas();
        return mapper.mapToList(metas, Meta.class);
    }
}