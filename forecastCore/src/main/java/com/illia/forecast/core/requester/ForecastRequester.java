package com.illia.forecast.core.requester;

import com.illia.forecast.core.model.WeatherForecast;
import com.illia.forecast.core.parser.WeatherXMLParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ForecastRequester implements WeatherForecastRequester{

    @Override
    public Mono<WeatherForecast> requestForecast(String url) {

        try {
            return WebClient.builder().baseUrl(url)
                    .build()
                    .get()
                    .accept(MediaType.APPLICATION_XML)
                    .retrieve()
                    .bodyToMono(String.class)
                    .map(content -> new WeatherXMLParser().parse(content));
        } catch (Exception ex) {
            log.error("request error, url:{}", url, ex);
            throw new RequesterException(ex);
        }

    }
}
