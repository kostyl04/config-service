package com.kostylenko.config_service.config_service_rest.domain.service.validator;

import com.kostylenko.config_service.config_service_rest.domain.model.Field;

public interface FieldValidator {

    boolean validate(Field field, Object value);

}
