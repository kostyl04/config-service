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

        public static final String CONFIG_ALREADY_EXISTS = "config.already.exists";
        public static final String CONFIG_NOT_FOUND = "config.not.found";
        public static final String CORRUPTED_CONFIG = "corrupted.config";
        public static final String NO_CONFIG_TO_UPDATE = "no.config.to.update";

        public static final String KEY_VALUE_CANNOT_BE_NULL = "key.value.cannot.be.null";
        public static final String VALUE_CAN_NOT_BE_NULL = "value.cannot.be.null";
        public static final String INVALID_PARSING_VALUE = "invalid.parsing.value";

        public static final String PARAMETER_ALREADY_EXISTS = "parameter.already.exists";
        public static final String PARAMETER_DOES_NOT_EXISTS = "parameter.does.not.exists";
    }
}