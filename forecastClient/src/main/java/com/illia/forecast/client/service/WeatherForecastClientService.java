package com.illia.forecast.client.service;


import com.illia.forecast.client.config.ForecastClientConfig;
import com.illia.forecast.client.model.ForecastExplanation;
import com.illia.forecast.client.requester.HttpClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class WeatherForecastClientService {

  private final ForecastClientConfig config;
  private final HttpClient client;

  public Mono<ForecastExplanation> getForecast(Short numberOfDays, Double lat, Double lon) {
    String urlWithParams = String.format("%s?number_of_days=%d&lat=%f&lon=%d", config.getUrl(),numberOfDays, lat, lon);
    return client.getForecast(urlWithParams);
  }
}
