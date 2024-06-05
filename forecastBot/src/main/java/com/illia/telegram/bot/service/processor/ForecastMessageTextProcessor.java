package com.illia.telegram.bot.service.processor;


import com.illia.telegram.bot.config.WeatherForecastConfig;
import com.illia.telegram.bot.model.MessageTextProcessorReply;
import com.illia.telegram.bot.requester.ForecastClient;
import com.illia.telegram.bot.service.processor.pattern.MessageTextPatterns;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.regex.Matcher;

@Component("forecastMessageTextProcessor")
@RequiredArgsConstructor
public class ForecastMessageTextProcessor implements MessageTextProcessor{

    private final ForecastClient client;

    private final WeatherForecastConfig config;

    @Override
    public Mono<MessageTextProcessorReply> process(String messageText) {
        try {
            var tokens = messageText.split(" ");

            var latitude = tokens.length == 1 ? "50.523" : tokens[0];
            var longitude = tokens.length == 1 ? "30.24" : tokens[1];
            var numberOfDays = tokens.length == 1 ? "2" : tokens[2];

            var url = String.format("%s?lat=%s&lon=%s&days=%s", config.getUrl(), latitude, longitude, numberOfDays);

            return client.getForecast(url)
                .map(x -> new MessageTextProcessorReply(x.toString(), null));
        }catch (Exception ex){
            return Mono.just(new MessageTextProcessorReply(null, "Enter valid format data, examples with /start"));
        }
    }
}
