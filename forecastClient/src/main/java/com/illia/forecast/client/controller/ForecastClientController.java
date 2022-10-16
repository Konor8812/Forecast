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

    @Autowired
    WeatherForecastClientService weatherForecastClientService;

    @GetMapping("v1/getForecast/{numberOfDays}/{lat}/{lon}")
    public Mono<WeatherForecast> getForecast(@PathVariable(name = "lat") Double latitude,
                                             @PathVariable(name = "lon")Double longitude,
                                             @PathVariable(name = "numberOfDays")Integer numberOfDays){
        System.out.println("forecast client controller called days: "+numberOfDays+ " lat " + latitude + " long " + longitude );
        return weatherForecastClientService.getForecast(latitude, longitude);
    }

    @GetMapping("/getForecast/{numberOfDays}/{lat}/{lon}")
    public Mono<String> getFormattedForecast(@PathVariable(name = "lat") Double latitude,
                                             @PathVariable(name = "lon")Double longitude,
                                             @PathVariable(name = "numberOfDays")Integer numberOfDays) {
        System.out.println("forecast client controller called for output days: " + numberOfDays + " lat " + latitude + " long " + longitude);
        return weatherForecastClientService.getForecastForOutput(latitude, longitude, numberOfDays);
    }
}
