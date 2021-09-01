package com.kostylenko.config_service.config_service_rest.domain.model.importing;

import com.google.common.collect.ImmutableList;
import com.kostylenko.config_service.config_service_rest.domain.model.Meta;
import lombok.Data;

import java.util.List;

import static java.util.Objects.isNull;

@Data
public class ImportData {

    private List<Meta> meta;
    private List<Config> configs;

    public List<Meta> getMeta() {
        return isNull(meta) ? ImmutableList.of() : meta;
    }

    public List<Config> getConfigs() {
        return isNull(configs) ? ImmutableList.of() : configs;
    }
}
