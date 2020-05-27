package com.kostylenko.config_service.config_service_rest.config;

import com.kostylenko.common.common_http.exception.message.TemplateSource;
import com.kostylenko.config_service.config_provider.common_config.Messages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class CommonHttpConfig {

    @Resource
    private Messages errors;

    @Bean
    public TemplateSource templateSource(){
        return (lang, code) -> errors.getMessage(code, lang);
    }
}
