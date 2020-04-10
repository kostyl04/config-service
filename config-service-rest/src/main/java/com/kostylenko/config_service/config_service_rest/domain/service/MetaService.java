package com.kostylenko.config_service.config_service_rest.domain.service;

import com.kostylenko.common.common_http.exception.BadRequestApiException;
import com.kostylenko.common.common_http.exception.NotFoundApiException;
import com.kostylenko.config_service.config_service_rest.data.repository.MetaRepository;
import com.kostylenko.config_service.config_service_rest.domain.model.Meta;
import com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages;
import com.kostylenko.config_service.config_service_rest.validator.MetaValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.kostylenko.common.common_mapper.domain.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Slf4j
@Service
@AllArgsConstructor
public class MetaService {

    private MetaRepository metaRepository;
    private MetaValidator metaValidator;
    private Mapper mapper;

    public Meta createMeta(Meta meta) {
        boolean exists = metaRepository.existsByName(meta.getName());
        if (exists) {
            log.warn("Meta {} already exists", meta.getName());
            throw new BadRequestApiException(ExceptionMessages.META_ALREADY_EXISTS);
        }
        metaValidator.validate(meta);
        var dataMeta = mapper.map(meta, com.kostylenko.config_service.config_service_rest.data.entity.Meta.class);
        metaRepository.save(dataMeta);
        return mapper.map(dataMeta, Meta.class);
    }

    public Meta getMeta(String name) {
        var meta = mapper.map(metaRepository.findByName(name), Meta.class);
        if (isNull(meta)) {
            log.warn("no meta with name {}", name);
            throw new NotFoundApiException(ExceptionMessages.NO_META_WITH_NAME + name);
        }
        return meta;
    }

    public List<Meta> getMetas() {
        List<Meta> metas = mapper.mapToList(metaRepository.findAll(), Meta.class);
        if (isEmpty(metas)) {
            log.warn("No metas");
            throw new NotFoundApiException(ExceptionMessages.NO_METAS);
        }
        return metas;
    }

}