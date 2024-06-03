package com.illia.telegram.bot.config;


import com.illia.telegram.bot.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Configuration
public class TelegramPollingScheduler {

    public TelegramPollingScheduler(TelegramService service, TelegramClientConfig config) {
        try (var executor = Executors.newSingleThreadScheduledExecutor()) {
            executor.scheduleAtFixedRate(service::getUpdates,
                    1000,
                    config.getRefreshRate(),
                    TimeUnit.MILLISECONDS);
            executor.shutdown();
        }
    }
}
