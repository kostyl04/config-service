package com.kostylenko.config_service.config_service_rest.rest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kostylenko.config_service.config_service_rest.domain.model.importing.ImportData;
import com.kostylenko.config_service.config_service_rest.domain.service.ImportDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/import")
public class ImportDataController {

    private final ObjectMapper objectMapper;
    private final ImportDataService importDataService;

    @PostMapping(consumes = APPLICATION_OCTET_STREAM_VALUE)
    public void importData(@RequestBody byte[] content) throws IOException {
        ImportData importData = objectMapper.readValue(content, ImportData.class);
        importDataService.importData(importData);
    }
}
