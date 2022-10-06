package com.illia.service.processor;


import com.illia.model.MessageTextProcessorReply;
import reactor.core.publisher.Mono;

public interface MessageTextProcessor {
    Mono<MessageTextProcessorReply> process(String messageText);
}
