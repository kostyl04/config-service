package com.kostylenko.config_service.config_service_rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kostylenko.config_service.config_provider.client.ImportDataClient;
import com.kostylenko.config_service.config_service_rest.domain.model.importing.ImportData;
import com.kostylenko.config_service.config_service_rest.domain.service.ImportDataService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InternalImportDataClient implements ImportDataClient {

    private final ObjectMapper objectMapper;
    private final ImportDataService importDataService;

    @Override
    @SneakyThrows
    public void importData(byte[] content) {
        importDataService.importData(objectMapper.readValue(content, ImportData.class));
    }
}
