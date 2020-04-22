package com.kostylenko.config_service.config_provider.client;

import com.kostylenko.config_service.config_provider.model.Config;
import com.kostylenko.config_service.config_provider.model.Parameter;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers("Content-Type: application/json")
public interface ConfigServiceClient {

    @RequestLine("GET /{appName}/{configName}/{version}")
    Config getConfig(@Param("appName") String appName,
                     @Param("configName") String configName,
                     @Param("version") String version);

    @RequestLine("GET /{appName}/{configName}/{version}/parameters/{name}")
    Parameter getParameter(@Param("appName") String appName,
                           @Param("configName") String configName,
                           @Param("version") String version,
                           @Param("name") String name);
}
