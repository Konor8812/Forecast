package com.illia.telegram.bot.client;


import reactor.core.publisher.Mono;

public interface ForecastClient {
    Mono<String> getForecast(String url, int numberOfDays);
}
