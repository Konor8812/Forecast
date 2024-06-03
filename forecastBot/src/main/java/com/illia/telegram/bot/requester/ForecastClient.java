package com.illia.telegram.bot.requester;

import reactor.core.publisher.Mono;

public interface ForecastClient {
    Mono<String> getForecast(String url, int numberOfDays);
}
