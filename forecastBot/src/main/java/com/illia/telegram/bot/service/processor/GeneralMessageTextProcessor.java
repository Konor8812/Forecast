package com.illia.telegram.bot.service.processor;

import com.illia.telegram.bot.model.MessageTextProcessorReply;
import com.illia.telegram.bot.service.processor.registry.BotCommandsRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component("generalMessageTextProcessor")
@RequiredArgsConstructor
public class GeneralMessageTextProcessor implements MessageTextProcessor {

    private final Map<String, MessageTextProcessor> processors;

    @Override
    public Mono<MessageTextProcessorReply> process(String messageText) {
        var processorName = "forecastMessageTextProcessor";
        for (BotCommandsRegistry cmd : BotCommandsRegistry.values()) {
            if (messageText.equals(cmd.getCommand())) {
               processorName = cmd.getProcessorName();
            }
        }
        return processors.get(processorName).process(messageText);
    }
}
