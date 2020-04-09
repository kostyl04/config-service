package com.kostylenko.config_service.config_service_rest.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {

    @UtilityClass
    public class LoggerMessages {

        public String null_set_of_fields = "Meta shouldn't have null set of fields";
        public String no_key_fields = "Meta should have at least one key field";
        public String no_meta_with_name = "no meta with name {}";
        public String meta_already_exists = "Meta {} already exists";

    }

    @UtilityClass
    public class ExceptionMessages {

        public String null_set_of_fields = "meta.has.null.set.of.fields";
        public String no_key_fields = "meta.has.no.key.fields";
        public String no_meta_with_name = "no.meta.with.name.";
        public String meta_already_exists = "meta.already.exists";

    }

}
