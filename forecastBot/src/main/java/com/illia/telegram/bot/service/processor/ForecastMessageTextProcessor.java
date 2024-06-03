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
        String url = "";
        int numberOfDays = 0;
        Matcher matcher;
        if((matcher = MessageTextPatterns.FULL.getPattern().matcher(messageText)).matches()){
            numberOfDays = Integer.parseInt(matcher.group(7));
            url = String.format("%s/%s/%s/%s", config.getUrl(), numberOfDays, matcher.group(1), matcher.group(4));

        }else if((matcher = MessageTextPatterns.SHORT.getPattern().matcher(messageText)).matches()){
            numberOfDays = Integer.parseInt(matcher.group(1));
            url = String.format("%s/%s/%s/%s", config.getUrl(), numberOfDays, "50.523", "30.24");
        } else {
            return Mono.just(new MessageTextProcessorReply(null, "Enter valid format data, examples with /start"));
        }
        var reply = client.getForecast(url, numberOfDays);
        return reply.map(x -> new MessageTextProcessorReply(x, null));
    }
}
