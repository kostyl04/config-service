package com.kostylenko.config_service.config_service_rest.domain.model.importing;

import com.kostylenko.config_service.config_service_rest.domain.model.Meta;
import lombok.Data;

import java.util.List;

@Data
public class ImportData {

    private List<Meta> meta;
    private List<Config> configs;
}
