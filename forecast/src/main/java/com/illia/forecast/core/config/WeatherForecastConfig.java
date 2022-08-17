package com.illia.forecast.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "weather")
public class WeatherForecastConfig {
    private String baseUrl;
    private String appid;
    private String mode;
}
