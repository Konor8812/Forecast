package com.illia.telegram.bot.service.processor;


import com.illia.telegram.bot.model.MessageTextProcessorReply;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component("startMessageTextProcessor")
public class StartMessageTextProcessor implements MessageTextProcessor {

    private final static String START_COMMAND_REPLY = """
            To get forecast enter either full form:
            `X Y N` - forecast for location, where
            X - decimal, location latitude
            Y - decimal, location longitude
            N - an integer from 1 to 5, numbers of days to show forecast for.
            Example:
            50.5 30.01 2
            or short form:
            `N`
            Simply a positive integer will return forecast for Irpin', UA for N days (max N is 5, min 1). \n
            Example:
            2
            """;

    @Override
    public Mono<MessageTextProcessorReply> process(String messageText) {
        return Mono.just(new MessageTextProcessorReply(START_COMMAND_REPLY, null));
    }
}
