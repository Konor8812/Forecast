package com.illia.forecast.core.service;


import com.illia.forecast.core.config.WeatherForecastConfig;
import com.illia.forecast.core.model.ForecastExplanation;
import com.illia.forecast.core.model.WeatherForecast;
import com.illia.forecast.core.requester.openweather.WeatherForecastRequester;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherForecastConfig weatherForecastConfig;
    private final WeatherForecastRequester forecastRequester;
    private final OpenAIService openAIService;

    public Mono<ForecastExplanation> getWeather(short daysAmount, double latitude, double longitude) {

        var weatherForecast = getWeatherForecast(latitude, longitude).map(wf -> wf.getForecastForNDays(daysAmount));
        return weatherForecast.flatMap(openAIService::getForecastExplanation);
    }

    private Mono<WeatherForecast> getWeatherForecast(double latitude, double longitude) {
        var url = getOpenWeatherMapUrl(latitude, longitude);
        return forecastRequester.requestForecast(url);
    }

    private String getOpenWeatherMapUrl(double latitude, double longitude) {
        return String.format("%s?lat=%f&lon=%f&appid=%s&mode=%s",
                weatherForecastConfig.getBaseUrl(),
                latitude,
                longitude,
                weatherForecastConfig.getAppid(),
                weatherForecastConfig.getMode());
    }

}
