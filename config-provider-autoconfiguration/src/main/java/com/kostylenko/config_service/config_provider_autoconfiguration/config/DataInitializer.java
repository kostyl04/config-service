package com.kostylenko.config_service.config_provider_autoconfiguration.config;

import com.kostylenko.config_service.config_provider.client.ImportDataClient;
import com.kostylenko.config_service.config_provider_autoconfiguration.model.ConfigProviderProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Files;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final ConfigProviderProperties properties;
    private final ImportDataClient importDataClient;

    @SneakyThrows
    @PostConstruct
    public void init() {
        boolean migrationEnabled = properties.getMigration().isEnabled();
        if (migrationEnabled) {
            log.info("Data initialization has been started");
            File file = new File(properties.getMigration().getFileLocation());
            byte[] content = Files.readAllBytes(file.toPath());
            importDataClient.importData(content);
            log.info("Data initialization completed");
            return;
        }
        log.info("Data initialization is disabled");
    }
}
