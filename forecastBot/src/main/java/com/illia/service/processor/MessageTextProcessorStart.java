package com.illia.service.processor;


import com.illia.model.MessageTextProcessorReply;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageTextProcessorStart implements MessageTextProcessor {

    private final static String START_COMMAND_REPLY = "To get forecast enter either full " +
            "LAT LON NOD - forecast for location for NOD days (5 at most)" +
            "or short" +
            "NOD - return forecast for Irpin for NOD days";

    @Override
    public Mono<MessageTextProcessorReply> process(String messageText) {
        return Mono.just(new MessageTextProcessorReply(START_COMMAND_REPLY, null));
    }
}
