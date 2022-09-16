package com.illia.forecast.client.controller;


import com.illia.forecast.client.service.WeatherForecastClientService;
import com.illia.forecast.core.model.WeatherForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "${client.baseUrl}")
public class ForecastClientController {

//http://localhost:8081/api/forecast/50.523/30.24

    @Autowired
    WeatherForecastClientService weatherForecastClientService;

    @GetMapping("/getForecast/{numberOfDays}/{lat}/{lon}")
    public Mono<String> getForecast(@PathVariable(name = "lat") Double latitude,
                                             @PathVariable(name = "lon")Double longitude,
                                             @PathVariable(name = "numberOfDays")Integer numberOfDays){
        System.out.println("forecast client controller called days: "+numberOfDays+ " lat " + latitude + " long " + longitude );
        WeatherForecast forecast = weatherForecastClientService.getForecast(latitude, longitude, numberOfDays).block();
        return Mono.just(forecast.getForecastForTimePeriod(numberOfDays));
    }
}
