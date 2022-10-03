package com.illia.forecast.core.service;


import com.illia.forecast.core.config.WeatherForecastConfig;
import com.illia.forecast.core.model.WeatherForecast;
import com.illia.forecast.core.parser.Parser;
import com.illia.forecast.core.requester.WeatherForecastRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    @Autowired
    private WeatherForecastConfig weatherForecastConfig;
    @Autowired
    private WeatherForecastRequester forecastRequester;


    public Mono<WeatherForecast> getWeather(double latitude, double longitude){

        String urlWithParams = String.format("%s?lat=%f&lon=%f&appid=%s&mode=%s", weatherForecastConfig.getBaseUrl(), latitude, longitude, weatherForecastConfig.getAppid(), weatherForecastConfig.getMode());

        return forecastRequester.requestForecast(urlWithParams);
    }
}
