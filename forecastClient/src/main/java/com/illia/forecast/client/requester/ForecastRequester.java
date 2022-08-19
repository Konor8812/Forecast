package com.illia.forecast.client.requester;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.forecast.core.model.WeatherForecast;
import com.illia.forecast.core.requester.RequesterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ForecastRequester {

    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public Mono<WeatherForecast> getForecast(String url) {
        try {
            return client.performRequest(url).map(this::parse);
        } catch (Exception ex) {
            log.error("request error, url:{}", url, ex);
            throw new RequesterException(ex);
        }

    }
    private WeatherForecast parse(String valuesToParse){
        try{
            return objectMapper.readValue(valuesToParse, WeatherForecast.class);
        } catch (JsonProcessingException e) {
            throw new ForecastClientException("Unable to parse string " + valuesToParse, e);
        }
    }

}
