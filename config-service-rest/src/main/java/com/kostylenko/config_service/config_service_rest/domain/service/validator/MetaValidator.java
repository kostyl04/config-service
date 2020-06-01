package com.kostylenko.config_service.config_service_rest.domain.service.validator;

import com.kostylenko.common.common_http.exception.BadRequestApiException;
import com.kostylenko.config_service.config_service_rest.domain.model.Field;
import com.kostylenko.config_service.config_service_rest.domain.model.Meta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.kostylenko.config_service.config_service_rest.util.Constant.ExceptionMessages.*;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Component
@Slf4j
public class MetaValidator {

    public void validate(Meta meta) {
        if (isNull(meta.getFields()) || isEmpty(meta.getFields())) {
            log.warn("Meta shouldn't have empty set of fields");
            throw new BadRequestApiException(EMPTY_META_FIELDS);
        }
        List<Field> keyFields = meta.getFields().stream().filter(Field::isKey).collect(toList());
        if (isEmpty(keyFields)) {
            log.warn("Meta should have at least one key field");
            throw new BadRequestApiException(KEY_FIELD_NOT_FOUND);
        }
        if (keyFields.size() == 1) {
            keyFields.forEach(field -> field.setIndex(0));
        } else {
            keyFields.forEach(keyField -> {
                if (isNull(keyField.getIndex())) {
                    log.warn("Key field index cannot be null");
                    throw new BadRequestApiException(KEY_FIELD_INDEX_IS_NULL);
                }
                long keyFieldCount = keyFields.stream().mapToInt(Field::getIndex).distinct().count();
                if (keyFieldCount != keyFields.size()) {
                    log.warn("Fields indexes should be unique");
                    throw new BadRequestApiException(KEY_FIELDS_INDEXES_ARE_NOT_UNIQUE);
                }
            });
        }
    }
}
