package com.kostylenko.config_service.config_service_rest.util;

public final class Constant {

    private Constant() {
    }

    public static class ExceptionMessages {

        private ExceptionMessages() {
        }

        public static final String EMPTY_META_FIELDS = "empty.meta.fields";
        public static final String KEY_FIELD_NOT_FOUND = "key.field.not.found";
        public static final String META_NOT_FOUND = "meta.not.found";
        public static final String META_ALREADY_EXISTS = "meta.already.exists";
        public static final String KEY_FIELD_INDEX_IS_NULL = "key.field.index.is.null";
        public static final String KEY_FIELDS_INDEXES_ARE_NOT_UNIQUE = "key.fields.indexes.are.not.unique";

        public static final String CONFIG_ALREADY_EXISTS = "config.already.exists";
        public static final String CONFIG_NOT_FOUND = "config.not.found";
        public static final String CORRUPTED_CONFIG = "corrupted.config";
        public static final String NO_CONFIG_TO_UPDATE = "no.config.to.update";

        public static final String KEY_VALUE_CANNOT_BE_NULL = "key.value.cannot.be.null";
        public static final String VALUE_CAN_NOT_BE_NULL = "value.cannot.be.null";

        public static final String PARAMETER_ALREADY_EXISTS = "parameter.already.exists";
        public static final String PARAMETER_DOES_NOT_EXISTS = "parameter.does.not.exist";
        public static final String PARAMETERS_NOT_FOUND = "parameters.not.found";

        public static final String VALUE_IS_NOT_VALID = "value.is.not.valid";
    }
}