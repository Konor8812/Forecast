package com.illia.telegram.bot.requester;

import com.illia.telegram.bot.model.ForecastExplanation;
import reactor.core.publisher.Mono;

public interface ForecastClient {

  Mono<ForecastExplanation> getForecast(String url);
}
