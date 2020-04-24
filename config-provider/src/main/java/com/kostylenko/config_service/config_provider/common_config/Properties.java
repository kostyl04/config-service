package com.kostylenko.config_service.config_provider.common_config;

import java.util.List;
import java.util.Map;

public interface Properties {

    Float getFloat(String name);

    Boolean getBoolean(String name);

    Long getLong(String name);

    String getString(String name);

    List<String> getList(String name);

    Map<String, String> getMap(String name);
}
