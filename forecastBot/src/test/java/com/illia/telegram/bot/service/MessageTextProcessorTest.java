package com.illia.telegram.bot.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.telegram.bot.requester.ForecastClientImpl;
import com.illia.telegram.bot.config.TelegramPollingScheduler;
import com.illia.telegram.bot.config.TelegramClientConfig;
import com.illia.telegram.bot.model.GetUpdatesResponse;
import com.illia.telegram.bot.service.processor.ForecastMessageTextProcessor;
import com.illia.telegram.bot.service.processor.GeneralMessageTextProcessor;
import com.illia.telegram.bot.service.processor.StartMessageTextProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MessageTextProcessorTest {

    @Autowired
    ObjectMapper mapper;
    @MockBean
    TelegramPollingScheduler appConfig;
    @MockBean
    TelegramClientConfig tconf;

    @Autowired
    @Qualifier("generalMessageTextProcessor")
    GeneralMessageTextProcessor generalMessageTextProcessor;

    @MockBean
    ForecastClientImpl client;

    @MockBean
    @Qualifier("forecastMessageTextProcessor")
    ForecastMessageTextProcessor forecastMessageTextProcessor;

    @MockBean
    StartMessageTextProcessor startMessageTextProcessor;


    @Test
    public void testMessageProcessing() throws IOException, URISyntaxException {

        URL path = ClassLoader.getSystemResource("provided.json").toURI().toURL();
        var providedResponses = mapper.readValue(path, GetUpdatesResponse.class);

        assertEquals(4, providedResponses.getResponses().size());

        providedResponses.getResponses()
                .forEach(x -> {
                    String text = x.getMessage().getText();
                    generalMessageTextProcessor.process(text);
                    if(text.matches("\\d") || text.matches("(\\d+(\\.\\d+)?) (\\d+(\\.\\d+)?) (\\d)")){
                        client.getForecast("", 0);
                    }
                });

        verify(startMessageTextProcessor, atLeast(1)).process(any());
        verify(forecastMessageTextProcessor, atLeast(3)).process(any());
        verify(client, atMostOnce()).getForecast(anyString(), anyInt());
    }
}
