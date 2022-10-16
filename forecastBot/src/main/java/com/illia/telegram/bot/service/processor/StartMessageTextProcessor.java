package com.illia.telegram.bot.service.processor;


import com.illia.telegram.bot.model.MessageTextProcessorReply;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Component("startMessageTextProcessor")
@Service
public class StartMessageTextProcessor implements MessageTextProcessor {

    private final static String START_COMMAND_REPLY = "To get forecast enter either full " +
            "LAT LON NOD - forecast for location," +
            " here LAT - decimal, location latitude, " +
            "LON - decimal, location longitude, " +
            "NOD - integer (1-5), numbers of days to show days. " +
            "Example: 50.5 30 2" +
            "or short " +
            "NOD (integer 1-5) - returns forecast for Irpin', UA for NOD days. " +
            "Example:  2";

    @Override
    public Mono<MessageTextProcessorReply> process(String messageText) {
        return Mono.just(new MessageTextProcessorReply(START_COMMAND_REPLY, null));
    }
}
