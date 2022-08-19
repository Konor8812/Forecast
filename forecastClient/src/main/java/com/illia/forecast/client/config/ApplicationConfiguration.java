package com.illia.forecast.client.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ForecastClientConfig.class)
public class ApplicationConfiguration {

}

