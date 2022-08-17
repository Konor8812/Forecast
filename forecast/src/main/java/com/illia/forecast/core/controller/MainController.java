package com.illia.forecast.core.controller;

import com.illia.forecast.core.model.WeatherForecast;
import com.illia.forecast.core.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/home")
public class MainController{

    @Autowired
    WeatherService weatherService;
    //http://api.openweathermap.org/data/2.5/forecast?lat=50.523&lon=30.24&appid=23b08b8bb2b1baec815f8e52eb970516&mode=xml

    @GetMapping("/forecast")
    public WeatherForecast getForecast(@RequestParam(name = "lat", required = false, defaultValue = "50.523") double latitude,
                                           @RequestParam(name = "lon", required = false, defaultValue = "30.24") double longitude){

        System.out.println("controller called");
        weatherService.getParsedWeather(latitude, longitude);

        return null;
    }
}
