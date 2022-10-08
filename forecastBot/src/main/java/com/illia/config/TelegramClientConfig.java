package com.illia.config;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Component
@Data
public class TelegramClientConfig {

    private static final String TELEGRAM_TOKEN_ENV_NAME = "TELEGRAM_TOKEN";
    private static final String TOKEN_FILE = "TOKEN_FILE";

    String token;

    String url;

    long refreshRate;


    public TelegramClientConfig(@Value("${app.telegram.url}") String url,
                                @Value("${app.telegram.refreshRate.millis}") long refreshRateMs) {
        String token = System.getenv(TELEGRAM_TOKEN_ENV_NAME);
        log.info("Received token :{}", token != null);
        if(token == null || token.isEmpty()){
            String fileLocation = System.getenv(TOKEN_FILE);
            token = readFile(fileLocation == null? "TOKEN_FILE.txt" : fileLocation);
            if(token == null || token.isEmpty()) {
                log.error("Telegram token wasn't received from {} or {}", TELEGRAM_TOKEN_ENV_NAME, TOKEN_FILE);
                throw new TelegramException("Can't get telegram token");
            }
        }
        this.token = token;
        this.url = url;
        this.refreshRate = refreshRateMs;
    }

    private String readFile(String tokenFileLocation) {
        try {
            return Files.readString(Paths.get(tokenFileLocation));
        } catch (IOException e) {
            log.error("can't read telegram token in :{}", TOKEN_FILE);
            throw new TelegramException("Can't read token file");
        }
    }
}
