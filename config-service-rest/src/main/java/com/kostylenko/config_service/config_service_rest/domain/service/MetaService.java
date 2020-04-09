package com.kostylenko.config_service.config_service_rest.domain.service;

import com.kostylenko.common.common_http.exception.BadRequestApiException;
import com.kostylenko.config_service.config_service_rest.data.repository.MetaRepository;
import com.kostylenko.config_service.config_service_rest.domain.model.Meta;
import com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages;
import com.kostylenko.config_service.config_service_rest.util.Constant.LoggerMessages;
import com.kostylenko.config_service.config_service_rest.validator.MetaValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.kostylenko.common.common_mapper.domain.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
            log.warn(LoggerMessages.meta_already_exists, meta.getName());
            throw new BadRequestApiException(ExceptionMessages.meta_already_exists);
        }
        metaValidator.validateMetaWithNoKeyFields(meta);
        var dataMeta = mapper.map(meta, com.kostylenko.config_service.config_service_rest.data.entity.Meta.class);
        metaRepository.save(dataMeta);
        return mapper.map(dataMeta, Meta.class);
    }

    public Meta getMeta(String name) {
        var meta = mapper.map(metaRepository.findByName(name), Meta.class);
        metaValidator.validateNullMeta(meta, name);
        return meta;
    }

    public List<Meta> getMetas(){
        return mapper.mapToList(metaRepository.findAll(), Meta.class);
    }

}
