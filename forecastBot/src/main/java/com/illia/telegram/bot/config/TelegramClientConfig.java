package com.illia.telegram.bot.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class TelegramClientConfig {

    @Value("${TELEGRAM_API_TOKEN}")
    private String token;
    @Value("${app.telegram.url}")
    private String url;
    @Value("${app.telegram.refreshRate.millis}")
    private long refreshRate;
    @Value("${app.telegram.get-updates.url")
    private String getUpdatesOperationUrl;
    @Value("${app.telegram.send-message.url")
    private String sendMessageOperationUrl;
}
