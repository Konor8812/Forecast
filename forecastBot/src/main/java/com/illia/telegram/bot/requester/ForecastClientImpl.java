package com.illia.telegram.bot.requester;

import com.illia.telegram.bot.config.WeatherForecastConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service("forecastClientImpl")
public class ForecastClientImpl implements ForecastClient {

    @Autowired
    WeatherForecastConfig config;

    WebClient.Builder builder = WebClient.builder();

    @Override
    public Mono<String> getForecast(String url, int numberOfDays) {
        return builder
                .baseUrl(url)
                .build()
                .get()
                .accept(MediaType.ALL)
                .retrieve()
                .bodyToMono(String.class);
    }

}

