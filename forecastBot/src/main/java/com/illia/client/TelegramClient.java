package com.illia.client;


import com.illia.model.GetUpdatesRequest;
import com.illia.model.GetUpdatesResponse;
import com.illia.model.SendMessageRequest;

public interface TelegramClient {

    GetUpdatesResponse getUpdates(GetUpdatesRequest request);
    void sendMessage(SendMessageRequest message);

}
