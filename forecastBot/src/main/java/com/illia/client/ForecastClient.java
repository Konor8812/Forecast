package com.illia.client;


import reactor.core.publisher.Mono;

public interface ForecastClient {
    Mono<String> getForecast(String url, int numberOfDays);
}
