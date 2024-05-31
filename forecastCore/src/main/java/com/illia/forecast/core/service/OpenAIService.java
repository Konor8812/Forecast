package com.illia.forecast.core.service;

import com.illia.forecast.core.model.ForecastExplanation;
import com.illia.forecast.core.model.WeatherForecast;
import com.illia.forecast.core.requester.openai.client.OpenAIAPICaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OpenAIService {

  private final OpenAIAPICaller apiCaller;

  public Mono<ForecastExplanation> getForecastExplanation(WeatherForecast weatherForecast) {
    return apiCaller.performAPIRequest(weatherForecast);
  }
}
