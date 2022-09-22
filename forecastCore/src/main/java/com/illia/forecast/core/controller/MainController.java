package com.illia.forecast.core.controller;

import com.illia.forecast.core.model.WeatherForecast;
import com.illia.forecast.core.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
//@RequestMapping(path = "${app.rest.api.prefix}")
@RequestMapping(path = "/home")
public class MainController{

    @Autowired
    public WeatherService weatherService;
    //http://api.openweathermap.org/data/2.5/forecast?lat=50.523&lon=30.24&appid=23b08b8bb2b1baec815f8e52eb970516&mode=xml

    @GetMapping("/forecast/{latitude}/{longitude}")
    public Mono<WeatherForecast> getForecast(@PathVariable(name = "latitude") Double latitude,
                                             @PathVariable(name = "longitude") Double longitude){
        System.out.println("main controller method called");
        WeatherForecast forecast = weatherService.getWeather(latitude, longitude);

        return Mono.just(forecast);
    }
}
