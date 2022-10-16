package com.illia.telegram.bot.service.processor;

import com.illia.telegram.bot.model.MessageTextProcessorReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Component("generalMessageTextProcessor")
@Service
public class GeneralMessageTextProcessor implements MessageTextProcessor {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    @Qualifier("forecastMessageTextProcessor")
    ForecastMessageTextProcessor forecastMessageTextProcessor;

    @Override
    public Mono<MessageTextProcessorReply> process(String messageText) {

        for (BotCommandsRegistry cmd : BotCommandsRegistry.values()) {
            if (messageText.equals(cmd.getCommand())) {
                var processor = applicationContext.getBean(cmd.getProcessorName(), MessageTextProcessor.class);
                return processor.process(messageText);
            }
        }
        return forecastMessageTextProcessor.process(messageText);
    }
}
