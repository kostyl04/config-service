package com.kostylenko.config_service.config_service_web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan("com.kostylenko.config_service.config_service_web")
public class ConfigServiceWebConfiguration implements WebMvcConfigurer {

}
