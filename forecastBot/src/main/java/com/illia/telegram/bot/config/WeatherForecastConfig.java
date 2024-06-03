package com.illia.telegram.bot.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class WeatherForecastConfig {
    @Value("${weather.forecast.client.url}")
    String url;
}
