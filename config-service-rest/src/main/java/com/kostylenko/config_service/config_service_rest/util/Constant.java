package com.kostylenko.config_service.config_service_rest.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {

    @UtilityClass
    public class ExceptionMessages {

        public String NULL_SET_OF_FIELDS = "meta.has.empty.set.of.fields";
        public String NO_KEY_FIELDS = "meta.has.no.key.fields";
        public String NO_META_WITH_NAME = "no.meta.with.name.";
        public String META_ALREADY_EXISTS = "meta.already.exists";
        public String NO_METAS = "no.metas";
    }

}