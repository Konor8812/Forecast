package com.illia.forecast.core.requester;

import com.illia.forecast.core.model.WeatherForecast;
import com.illia.forecast.core.parser.WeatherXMLParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ForecastRequester implements WeatherForecastRequester{

    @Autowired
    private WeatherXMLParser weatherXMLParser;

    @Override
    public Mono<WeatherForecast> requestForecast(String url) {

        try {
            return WebClient.builder().baseUrl(url)
                    .build()
                    .get()
                    .accept(MediaType.APPLICATION_XML)
                    .retrieve()
                    .bodyToMono(String.class)
                    .onErrorReturn(error -> error.getMessage().startsWith("500 Internal Server Error from GET"),"Service currently is unavailable")
                    .onErrorReturn(error -> error.getMessage().startsWith("400 Bad Request from GET"),"Enter valid data to proceed: latitude and longitude should be in -180...180")
                    .map(content -> weatherXMLParser.parse(content));
        } catch (Exception ex) {
            log.error("request error, url:{}", url, ex);
            throw new RequesterException(ex);
        }
    }

}
