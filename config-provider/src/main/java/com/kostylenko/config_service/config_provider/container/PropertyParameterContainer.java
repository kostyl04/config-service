package com.kostylenko.config_service.config_provider.container;

import com.kostylenko.config_service.config_provider.common_config.Properties;
import com.kostylenko.config_service.config_provider.common_config.Property;
import com.kostylenko.config_service.config_provider.model.ConfigKey;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static java.util.Objects.isNull;

@Slf4j
public class PropertyParameterContainer extends GenericParameterContainer<Property> implements Properties {

    public PropertyParameterContainer(ConfigKey configKey, String beanName) {
        super(configKey, Property.class, beanName);
    }

    @Override
    public Float getFloat(String name) {
        Property property = findProperty(name);
        accepts(property, Float.class);
        return Float.valueOf(property.getValue());
    }

    @Override
    public Boolean getBoolean(String name) {
        Property property = findProperty(name);
        accepts(property, Boolean.class);
        return Boolean.valueOf(property.getValue());
    }

    @Override
    public Long getLong(String name) {
        Property property = findProperty(name);
        accepts(property, Long.class);
        return Long.valueOf(property.getValue());
    }

    @Override
    public String getString(String name) {
        Property property = findProperty(name);
        accepts(property, String.class);
        return property.getValue();
    }

    @Override
    public List<String> getList(String name) {
        Property property = findProperty(name);
        accepts(property, List.class);
        String stringList = property.getValue();
        String[] values = stringList.substring(1, stringList.length() - 1).replace(" ", "").split(",");
        return new ArrayList<>(Arrays.asList(values));
    }

    @Override
    public Map<String, String> getMap(String name) {
        Property property = findProperty(name);
        accepts(property, Map.class);
        Map<String, String> result = new HashMap<>();
        String stringMap = property.getValue();
        String[] entries = stringMap.substring(1, stringMap.length() - 1).replace(" ", "").split(",");
        for (String entry : entries) {
            String[] keyValue = entry.split("=");
            result.put(keyValue[0], keyValue[1]);
        }
        return result;
    }

    private Property findProperty(String name) {
        Property property = get(name);
        if (isNull(property)) {
            throw new RuntimeException(String.format("Property '%s' not found", name));
        }
        return property;
    }

    private void accepts(Property property, Class clazz) {
        boolean accepts = property.getType().equalsIgnoreCase(clazz.getSimpleName());
        if (!accepts) {
            throw new IllegalArgumentException(String.format("Property '%s' don't accepts type '%s' ('%s' expected)", property.getName(), clazz.getSimpleName(), property.getType()));
        }
    }
}
