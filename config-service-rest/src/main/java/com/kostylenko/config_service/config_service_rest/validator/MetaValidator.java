package com.kostylenko.config_service.config_service_rest.validator;

import com.kostylenko.common.common_http.exception.BadRequestApiException;
import com.kostylenko.common.common_http.exception.NotFoundApiException;
import com.kostylenko.config_service.config_service_rest.domain.model.Field;
import com.kostylenko.config_service.config_service_rest.domain.model.Meta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

import com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages;
import com.kostylenko.config_service.config_service_rest.util.Constant.LoggerMessages;
import static java.util.Objects.isNull;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Component
@Slf4j
public class MetaValidator {

    public void validateMetaWithNoKeyFields(Meta meta) {
        if (isNull(meta.getFields())) {
            log.warn(LoggerMessages.null_set_of_fields);
            throw new BadRequestApiException(ExceptionMessages.null_set_of_fields);
        }
        Optional<Field> first = meta.getFields().stream().filter(Field::isKey).findFirst();
        if (isEmpty(meta.getFields()) || first.isEmpty()) {
            log.warn(LoggerMessages.no_key_fields);
            throw new BadRequestApiException(ExceptionMessages.no_key_fields);
        }
    }

    public void validateNullMeta(Meta meta, String name) {
        if (isNull(meta)) {
            log.warn(LoggerMessages.no_meta_with_name, name);
            throw new NotFoundApiException(ExceptionMessages.no_meta_with_name + name);
        }
    }

}
