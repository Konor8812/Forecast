package com.illia.telegram.bot.service.processor;


import com.illia.telegram.bot.model.MessageTextProcessorReply;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component("startMessageTextProcessor")
public class StartMessageTextProcessor implements MessageTextProcessor {

    private final static String START_COMMAND_REPLY = """
            To get forecast enter either full form: \n
            `X Y N` - forecast for location, where \n
            X - decimal, location latitude \n
            Y - decimal, location longitude \n
            N - an integer from 1 to 5, numbers of days to show forecast for. \n
            Example: \n
            50.5 30.01 2 \n
            or short form: \n
            `N` \n
            Simply an integer from 1 to 5 will return forecast for Irpin', UA for N days. \n
            Example: \n
            2
            """;

    @Override
    public Mono<MessageTextProcessorReply> process(String messageText) {
        return Mono.just(new MessageTextProcessorReply(START_COMMAND_REPLY, null));
    }
}
