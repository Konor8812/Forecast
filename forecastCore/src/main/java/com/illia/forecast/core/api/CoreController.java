package com.illia.forecast.core.api;

import com.illia.forecast.core.model.ForecastExplanation;
import com.illia.forecast.core.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class CoreController {

    public final WeatherService weatherService;

    @GetMapping("/forecast")
    public Mono<ForecastExplanation> getForecast(@RequestParam(name = "lat") Double latitude,
                                                 @RequestParam(name = "lon") Double longitude,
                                                 @RequestParam(name = "days") Short daysAmount) {
        return weatherService.getWeather(daysAmount, latitude, longitude);
    }
}
