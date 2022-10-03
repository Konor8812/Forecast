package com.illia.forecast.client.service;


import com.illia.forecast.client.config.ForecastClientConfig;
import com.illia.forecast.client.requester.WeatherForecastRequester;

import com.illia.forecast.core.model.WeatherForecast;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class WeatherForecastClientService {

    private final ForecastClientConfig config;
    private final WeatherForecastRequester requester;


    public Mono<WeatherForecast> getForecast(Double lat, Double lon){
        String urlWithParams = String.format("%s/%f/%f", config.getUrl(), lat, lon);
        System.out.println("url to go " + urlWithParams);
        return requester.getForecast(urlWithParams);
    }

    public Mono<String> getForecastForOutput(Double lat, Double lon, int numOfDaysToShow){
        String urlWithParams = String.format("%s/%f/%f", config.getUrl(), lat, lon);
        System.out.println("url to go " + urlWithParams);
        return requester.getForecast(urlWithParams).map(wf -> wf.getForecastForTimePeriod(numOfDaysToShow));
    }

}
