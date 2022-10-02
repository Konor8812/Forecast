package com.illia.forecast.core.controller;

import com.illia.forecast.core.model.WeatherForecast;
import com.illia.forecast.core.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(path = "/home")
public class MainController{

    @Autowired
    public WeatherService weatherService;
    //http://api.openweathermap.org/data/2.5/forecast?lat=50.523&lon=30.24&appid=23b08b8bb2b1baec815f8e52eb970516&mode=xml

    @GetMapping(value = "/forecast/{latitude}/{longitude}")
    public Mono<WeatherForecast> getForecast(@PathVariable(name = "latitude") Double latitude,
                                             @PathVariable(name = "longitude") Double longitude){
        System.out.println("main controller method called");

        return weatherService.getWeather(latitude, longitude);
    }
}
