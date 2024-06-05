package com.illia.telegram.bot.requester;

import com.illia.telegram.bot.config.WeatherForecastConfig;
import com.illia.telegram.bot.model.ForecastExplanation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ForecastClientImpl implements ForecastClient {
    private final WebClient webClient;

    @Override
    public Mono<ForecastExplanation> getForecast(String url) {
        return webClient
            .get()
            .uri(url)
            .retrieve()
            .bodyToMono(ForecastExplanation.class);
    }
}

