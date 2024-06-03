package com.illia.forecast.client.api;


import com.illia.forecast.client.model.ForecastExplanation;
import com.illia.forecast.client.service.WeatherForecastClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ForecastClientController {

    private final WeatherForecastClientService weatherForecastClientService;

    @GetMapping("/getForecast")
    public Mono<ForecastExplanation> getForecast(@RequestParam(name = "lat") Double latitude,
                                                 @RequestParam(name = "lon") Double longitude,
                                                 @RequestParam(name = "days") Short numberOfDays) {
        return weatherForecastClientService.getForecast(numberOfDays, latitude, longitude);
    }
}
