package com.illia.telegram.bot.service.processor;


import com.illia.telegram.bot.config.WeatherForecastConfig;
import com.illia.telegram.bot.model.MessageTextProcessorReply;
import com.illia.telegram.bot.requester.ForecastClient;
import com.illia.telegram.bot.service.processor.pattern.MessageTextPatterns;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

import static com.illia.telegram.bot.service.processor.pattern.MessageTextPatterns.SHORT_PROMPT_REQUEST_PATTERN;
import static com.illia.telegram.bot.service.processor.pattern.MessageTextPatterns.FULL_PROMPT_REQUEST_PATTERN;

@Component("forecastMessageTextProcessor")
@RequiredArgsConstructor
public class ForecastMessageTextProcessor implements MessageTextProcessor {

//    private static final Pattern FULL_PROMPT_REQUEST_PATTERN = Pattern.compile("((\\d+)(\\.\\d+)?) ((\\d+)(.\\d+)?) (\\d)");
//    private static final Pattern SHORT_PROMPT_REQUEST_PATTERN = Pattern.compile("(\\d)");
    private final ForecastClient client;
    private final WeatherForecastConfig config;

    @Override
    public Mono<MessageTextProcessorReply> process(String messageText) {
        try {
            var urlBuilder = new StringBuilder(config.getUrl()).append("?");

            var matcher = FULL_PROMPT_REQUEST_PATTERN.getPattern().matcher(messageText);
            if (matcher.matches()) {
                urlBuilder.append("lat=").append(matcher.group(1))
                        .append("&lon=").append(matcher.group(4))
                        .append("&days=").append(matcher.group(7));
            } else {
                matcher = SHORT_PROMPT_REQUEST_PATTERN.getPattern().matcher(messageText);
                if (matcher.matches()) {
                    urlBuilder.append("lat=").append("50.523")
                            .append("&lon=").append("30.24")
                            .append("&days=").append(matcher.group(1));
                } else {
                    return Mono.just(new MessageTextProcessorReply(null, "Enter valid format data, examples with /start"));
                }
            }

            return client.getForecast(urlBuilder.toString())
                    .map(x -> new MessageTextProcessorReply(x.toString(), null));
        } catch (Exception ex) {
            return Mono.just(new MessageTextProcessorReply(null, "Enter valid format data, examples with /start"));
        }
    }
}
