package com.illia.forecast.client.requester;


import com.illia.forecast.client.model.ForecastExplanation;
import reactor.core.publisher.Mono;

public interface HttpClient {

  Mono<ForecastExplanation> getForecast(String url);
}
