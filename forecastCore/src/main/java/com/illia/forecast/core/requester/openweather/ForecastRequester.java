package com.illia.forecast.core.requester.openweather;

import com.illia.forecast.core.model.WeatherForecast;
import com.illia.forecast.core.parser.WeatherXMLParser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ForecastRequester implements WeatherForecastRequester {

    private final WeatherXMLParser weatherXMLParser;

    @Override
    public Mono<WeatherForecast> requestForecast(String url) {

        try {
            return WebClient.builder().baseUrl(url)
                    .build()
                    .get()
                    .accept(MediaType.APPLICATION_XML)
                    .retrieve()
                    .bodyToMono(String.class)
                    .onErrorReturn(
                            error -> error.getMessage().startsWith("500 Internal Server Error from GET"),
                            "Service currently is unavailable")
                    .onErrorReturn(error -> error.getMessage().startsWith("400 Bad Request from GET"),
                            "Enter valid data to proceed: latitude and longitude should be in -180...180")
                    .map(weatherXMLParser::parse);
        } catch (Exception ex) {
            throw new RequesterException(ex);
        }
    }

}
