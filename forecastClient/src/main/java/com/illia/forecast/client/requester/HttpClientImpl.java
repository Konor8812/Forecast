package com.illia.forecast.client.requester;


import com.illia.forecast.core.model.WeatherForecast;
import com.illia.forecast.core.requester.RequesterException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@Slf4j
public class HttpClientImpl implements HttpClient {

    @Override
    public Mono<WeatherForecast> performRequest(String url) {
        try {
            return WebClient.builder()
                    .baseUrl(url)
                    .build()
                    .get()
                    .accept(MediaType.ALL)
                    .retrieve()
                    .bodyToMono(WeatherForecast.class);

        } catch (Exception ex) {
            log.error("request error, url:{}", url, ex);
            throw new RequesterException(ex);
        }

    }

}
