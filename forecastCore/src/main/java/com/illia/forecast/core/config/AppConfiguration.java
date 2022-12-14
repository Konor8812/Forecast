package com.illia.forecast.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WeatherForecastConfig.class)
public class AppConfiguration {

}
