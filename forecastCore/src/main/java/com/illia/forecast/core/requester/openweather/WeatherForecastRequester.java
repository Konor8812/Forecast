package com.illia.forecast.core.requester.openweather;


import com.illia.forecast.core.model.WeatherForecast;
import reactor.core.publisher.Mono;

public interface WeatherForecastRequester {

    Mono<WeatherForecast> requestForecast(String url);
}
