package com.illia.service;


import com.illia.client.TelegramClient;
import com.illia.config.TelegramClientConfig;
import com.illia.model.GetUpdatesRequest;
import com.illia.service.processor.MessageTextProcessor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Value
@Service
@RequiredArgsConstructor
public class TelegramServiceImpl implements TelegramService {

    TelegramClient client;
    MessageTextProcessor generalProcessor;
    LastUpdateIdKeeper idKeeper;

    @Override
    public void getUpdates() {
        System.out.println("getting updates ");
        var responce = client.getUpdates(new GetUpdatesRequest(idKeeper.getUpdateId()));

    }
}
