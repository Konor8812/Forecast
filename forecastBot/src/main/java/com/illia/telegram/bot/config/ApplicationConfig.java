package com.illia.telegram.bot.config;


import com.illia.telegram.bot.client.TelegramClient;
import com.illia.telegram.bot.service.LastUpdateIdKeeper;
import com.illia.telegram.bot.service.TelegramService;
import com.illia.telegram.bot.service.TelegramServiceImpl;
import com.illia.telegram.bot.service.processor.MessageTextProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@EnableConfigurationProperties(WeatherForecastConfig.class)
public class ApplicationConfig {

    @Autowired
    TelegramClientConfig config;

    @Bean
    public TelegramScheduler telegramScheduler(TelegramClient client,
                                               TelegramClientConfig config,
                                               @Qualifier("generalMessageTextProcessor") MessageTextProcessor generalTextProcessor,
                                               LastUpdateIdKeeper lastUpdateIdKeeper){
        TelegramService service = new TelegramServiceImpl(client, generalTextProcessor, lastUpdateIdKeeper);
        return new TelegramScheduler(service, config);
    }

    public static class TelegramScheduler{
        public TelegramScheduler(TelegramService service, TelegramClientConfig config){
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.scheduleAtFixedRate(service::getUpdates, 1000, config.getRefreshRate(), TimeUnit.MILLISECONDS);
        }


    }
}
