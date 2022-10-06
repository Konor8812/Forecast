package com.illia.service.processor;


import com.illia.client.ForecastClient;
import com.illia.config.WeatherForecastConfig;
import com.illia.model.MessageTextProcessorReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.regex.Matcher;

@Service
public class ForecastMessageTextProcessor implements MessageTextProcessor{

    @Autowired
    ForecastClient client;

    @Autowired
    WeatherForecastConfig config;

    @Override
    public Mono<MessageTextProcessorReply> process(String messageText) {
        String url = "";
        int numberOfDays = 0;
        Matcher matcher;
        if((matcher = MessageTextPatterns.FULL.getPattern().matcher(messageText)).matches()){
            System.out.println(matcher.group(5));
            numberOfDays = Integer.parseInt(matcher.group(5));
            url = String.format("%s/%s/%s/%s", config.getUrl(), numberOfDays, matcher.group(1), matcher.group(3));

        }else if((matcher = MessageTextPatterns.SHORT.getPattern().matcher(messageText)).matches()){
            numberOfDays = Integer.parseInt(matcher.group(1));
            System.out.println(numberOfDays);
            url = String.format("%s/%s/%s/%s", config.getUrl(), numberOfDays, "50.523", "30.24");
        } else {
            return Mono.just(new MessageTextProcessorReply(null, "Enter valid format data, examples with /start"));
        }
        var reply = client.getForecast(url, numberOfDays);
        return reply.map(x -> new MessageTextProcessorReply(x, null));
    }
}
