package com.illia.forecast.client.requester;


import com.illia.forecast.core.model.WeatherForecast;
import reactor.core.publisher.Mono;

public interface HttpClient {
    Mono<WeatherForecast> performRequest(String url);
}
