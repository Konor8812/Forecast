package com.illia.forecast.core.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class WeatherForecastConfig {

  @Value("${weather.baseUrl}")
  private String baseUrl;
  @Value("${weather.appid}")
  private String appid;
  @Value("${weather.mode}")
  private String mode;
}
