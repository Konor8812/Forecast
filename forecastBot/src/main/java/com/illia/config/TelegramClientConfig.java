package com.illia.config;


import lombok.Value;

@Value
public class TelegramClientConfig {
    String token;
    String url;
    long refreshRate;

}
