package com.illia.forecast.core.controller;

import com.illia.forecast.core.model.WeatherForecast;
import com.illia.forecast.core.parser.Parser;
import com.illia.forecast.core.parser.WeatherXMLParser;
import com.illia.forecast.core.requester.openweather.ForecastRequester;
import com.illia.forecast.core.service.WeatherService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CoreControllerTest {

    @Autowired
    private WeatherService service;

    @MockBean
    private ForecastRequester requester;

    @Test
    public void getForecast() throws IOException, URISyntaxException {

        URI uri = ClassLoader.getSystemResource("xmlResponse.xml").toURI();
        String weatherXML = Files.readString(Paths.get(uri), Charset.forName("Windows-1251"));

        Parser parser = new WeatherXMLParser();

        WeatherForecast forecast = parser.parse(weatherXML);

        when(requester.requestForecast(any())).thenReturn(Mono.just(forecast));

        var weatherForecast = service.getWeather(0, 0).block();
        verify(requester, atLeastOnce()).requestForecast(any());
        assertNotNull(weatherForecast);
        assertTrue(weatherForecast.toString().split("Weather").length >= 40);
    }

}