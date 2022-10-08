package com.illia.config;


import com.illia.client.TelegramClient;
import com.illia.service.LastUpdateIdKeeper;
import com.illia.service.TelegramService;
import com.illia.service.TelegramServiceImpl;
import com.illia.service.processor.MessageTextProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@EnableConfigurationProperties(WeatherForecastConfig.class)
public class ApplicationConfig {

    @Autowired
    TelegramClientConfig config;

//    @Bean("telegramClientConfig")
//    public TelegramClientConfig telegramClientConfig(@Value("${app.telegram.url}") String url,
//                                                     @Value("${app.telegram.refreshRate.millis}") long refreshRateMs) {
//        String token = System.getProperty("telegramToken");
//        if (token == null) {
//            token = readFile(TOKEN_FILE_LOCATION);
//        }
//        if (token == null) {
//            log.error("telegram token not found");
//        }
//        return new TelegramClientConfig(token, url, refreshRateMs);
//    }

//    private String readFile(String tokenFileLocation) {
//        try {
//            return Files.readString(Paths.get(tokenFileLocation));
//        } catch (IOException e) {
//            log.error("can't read telegram token in :{}", TOKEN_FILE_LOCATION);
//            return null;
//        }
//    }

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
