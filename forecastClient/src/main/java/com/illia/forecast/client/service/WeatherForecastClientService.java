package com.illia.forecast.client.service;


import com.illia.forecast.client.config.ForecastClientConfig;
import com.illia.forecast.client.requester.ForecastRequester;
import com.illia.forecast.core.model.WeatherForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class WeatherForecastClientService {

    @Autowired
    private ForecastClientConfig config;
    @Autowired
    private ForecastRequester requester;

    public Mono<WeatherForecast> getForecast(Double lat, Double lon, Integer numOfDays){

        String urlWithParams = String.format("%s/%f/%f", config.getUrl(), lat, lon);

        return requester.getForecast(urlWithParams);
    }

}
