package com.illia.forecast.client.requester;


import com.illia.forecast.core.model.WeatherForecast;
import com.illia.forecast.core.requester.RequesterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherForecastRequester {

    private final HttpClient client;

    public Mono<WeatherForecast> getForecast(String url) {
        try {
            return client.performRequest(url);
        } catch (Exception ex) {
            log.error("request error, url:{}", url, ex);
            throw new RequesterException(ex);
        }

    }



}
