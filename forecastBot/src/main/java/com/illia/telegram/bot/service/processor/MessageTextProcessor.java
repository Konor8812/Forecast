package com.illia.telegram.bot.service.processor;


import com.illia.telegram.bot.model.MessageTextProcessorReply;
import reactor.core.publisher.Mono;

public interface MessageTextProcessor {
    Mono<MessageTextProcessorReply> process(String messageText);
}
