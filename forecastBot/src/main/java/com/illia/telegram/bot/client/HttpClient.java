package com.illia.telegram.bot.client;

public interface HttpClient {
    String performRequest(String url, String params);

}
