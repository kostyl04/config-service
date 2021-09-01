package com.kostylenko.config_service.config_provider.client;

import feign.Headers;
import feign.RequestLine;

@Headers("Content-Type: application/octet-stream")
public interface ImportDataClient {

    @RequestLine("POST /import")
    void importData(byte[] content);
}
