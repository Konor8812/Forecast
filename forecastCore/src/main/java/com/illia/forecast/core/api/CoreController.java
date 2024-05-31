package com.illia.forecast.core.api;

import com.illia.forecast.core.model.ForecastExplanation;
import com.illia.forecast.core.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/forecast")
public class CoreController {

  public final WeatherService weatherService;

  @GetMapping(value = "/{latitude}/{longitude}")
  public Mono<ForecastExplanation> getForecast(@PathVariable(name = "latitude") Double latitude,
      @PathVariable(name = "longitude") Double longitude) {
    return weatherService.getWeather(latitude, longitude);
  }
}
