package com.kostylenko.config_service.config_service_rest.validator;

import com.kostylenko.common.common_http.exception.BadRequestApiException;
import com.kostylenko.config_service.config_service_rest.domain.model.Field;
import com.kostylenko.config_service.config_service_rest.domain.model.Meta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

import com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages;

import static java.util.Objects.isNull;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Component
@Slf4j
public class MetaValidator {

    public void validate(Meta meta) {
        if (isNull(meta.getFields()) || isEmpty(meta.getFields())) {
            log.warn("Meta shouldn't have empty set of fields");
            throw new BadRequestApiException(ExceptionMessages.NULL_SET_OF_FIELDS);
        }
        boolean isEmpty = meta.getFields().stream().filter(Field::isKey).findFirst().isEmpty();
        if (isEmpty) {
            log.warn("Meta should have at least one key field");
            throw new BadRequestApiException(ExceptionMessages.NO_KEY_FIELDS);
        }
    }

}
