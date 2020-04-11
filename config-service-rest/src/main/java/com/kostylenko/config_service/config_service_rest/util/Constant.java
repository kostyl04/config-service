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
    }
}