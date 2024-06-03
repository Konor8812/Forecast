package com.illia.forecast.client.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class ForecastClientConfig {

  @Value("${forecast-core.url}")
  private String url;
}
