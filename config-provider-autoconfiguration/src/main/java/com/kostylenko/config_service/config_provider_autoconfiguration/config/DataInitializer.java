package com.kostylenko.config_service.config_provider_autoconfiguration.config;

import com.google.common.collect.ImmutableList;
import com.kostylenko.config_service.config_provider.client.ImportDataClient;
import com.kostylenko.config_service.config_provider_autoconfiguration.model.ConfigProviderProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

@Slf4j
@Component
@DependsOn("mongobee")
@RequiredArgsConstructor
public class DataInitializer {

    private final ConfigProviderProperties properties;
    private final ImportDataClient importDataClient;

    private static final String MIGRATION_FILE_EXTENSION = ".json";
    private static final String MIGRATION_FILE_NAME_START_WITH_PATTERN = "^(V[0-9]+__).*$";


    @SneakyThrows
    @PostConstruct
    public void init() {
        boolean migrationEnabled = properties.getMigration().isEnabled();
        if (migrationEnabled) {
            File folder = new File(properties.getMigration().getDataLocation());
            List<File> migrationFiles = listMigrationFiles(folder);
            if (!migrationFiles.isEmpty()) {
                log.info("Data initialization has been started");
                migrationFiles.forEach(this::importData);
                log.info("Data initialization completed");
            }
            return;
        }
        log.info("Data initialization is disabled");
    }

    @SuppressWarnings("ConstantConditions")
    public List<File> listMigrationFiles(File folder) {
        File[] files = nonNull(folder.listFiles()) ? folder.listFiles() : new File[]{};
        List<File> migrationFiles = Arrays.stream(files)
                .filter(file -> file.getName().matches(MIGRATION_FILE_NAME_START_WITH_PATTERN)
                        && file.getName().endsWith(MIGRATION_FILE_EXTENSION))
                .sorted(Comparator.comparing(this::getMigrationFileVersion))
                .collect(toList());
        if (migrationFiles.isEmpty()) {
            log.warn("No migration files were found in the predefined directory: {}", folder.toPath());
            return ImmutableList.of();
        }
        if (migrationFiles.size() < files.length) {
            log.warn("Some files from the predefined directory ({}) don't correspond to migration file name rules, so they won't covered by migration", folder.toPath());
        }
        return migrationFiles;
    }

    private Integer getMigrationFileVersion(File file) {
        return Integer.valueOf(file.getName().split("__")[0].substring(1));
    }

    @SneakyThrows
    private void importData(File file) {
        byte[] content = Files.readAllBytes(file.toPath());
        importDataClient.importData(content);
        log.info("Changeset has been applied: {}", file.getName());
    }
}
