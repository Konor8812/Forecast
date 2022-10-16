package com.illia.telegram.bot.client;


import com.illia.telegram.bot.model.GetUpdatesRequest;
import com.illia.telegram.bot.model.GetUpdatesResponse;
import com.illia.telegram.bot.model.SendMessageRequest;

public interface TelegramClient {

    GetUpdatesResponse getUpdates(GetUpdatesRequest request);
    void sendMessage(SendMessageRequest message);

}
