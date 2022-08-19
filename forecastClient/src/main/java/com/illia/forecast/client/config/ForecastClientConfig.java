package com.illia.forecast.client.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "weather-forecast-client")
public class ForecastClientConfig {
    String url;
}
