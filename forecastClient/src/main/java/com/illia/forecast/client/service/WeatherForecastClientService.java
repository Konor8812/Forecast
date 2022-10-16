package com.illia.forecast.client.service;


import com.illia.forecast.client.config.ForecastClientConfig;
import com.illia.forecast.client.requester.WeatherForecastClient;

import com.illia.forecast.core.model.WeatherForecast;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class WeatherForecastClientService {

    private final ForecastClientConfig config;
    private final WeatherForecastClient requester;


    public Mono<WeatherForecast> getForecast(Double lat, Double lon){
        String urlWithParams = String.format("%s/%f/%f", config.getUrl(), lat, lon);
        log.info("url to go :{}", urlWithParams);
        return requester.getForecast(urlWithParams);
    }

    public Mono<String> getForecastForOutput(Double lat, Double lon, int numOfDaysToShow){
        String urlWithParams = String.format("%s/%f/%f", config.getUrl(), lat, lon);
        log.info("url to go :{}", urlWithParams);
        return requester.getForecast(urlWithParams).map(wf -> wf.getForecastForTimePeriod(numOfDaysToShow));
    }

}
