package com.illia.forecast.client.service;


import com.illia.forecast.client.config.ForecastClientConfig;
import com.illia.forecast.client.model.ForecastExplanation;
import com.illia.forecast.client.requester.HttpClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WeatherForecastClientService {

  private final ForecastClientConfig config;
  private final HttpClient client;

  public Mono<ForecastExplanation> getForecast(Short numberOfDays, Double lat, Double lon) {
    var urlWithParams = getUrl(numberOfDays, lat, lon);
    return client.getForecast(urlWithParams);
  }

  private String getUrl(Short numberOfDays, Double lat, Double lon) {
    return String.format("%s?days=%d&lat=%f&lon=%f", config.getUrl(), numberOfDays, lat, lon);
  }
}
