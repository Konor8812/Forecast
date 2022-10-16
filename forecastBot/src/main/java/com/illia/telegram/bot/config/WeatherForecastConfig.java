package com.illia.telegram.bot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "weather.forecast.client")
public class WeatherForecastConfig {
    String url;
}
