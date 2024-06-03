package com.illia.forecast.client.controller;


import com.illia.forecast.client.model.ForecastExplanation;
import com.illia.forecast.client.service.WeatherForecastClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
public class ForecastClientController {

  private final WeatherForecastClientService weatherForecastClientService;

  @GetMapping("getForecast/{numberOfDays}/{lat}/{lon}")
  public Mono<ForecastExplanation> getForecast(@PathVariable(name = "lat") Double latitude,
      @PathVariable(name = "lon") Double longitude,
      @PathVariable(name = "numberOfDays") Short numberOfDays) {
    return weatherForecastClientService.getForecast(numberOfDays, latitude, longitude);
  }
}
