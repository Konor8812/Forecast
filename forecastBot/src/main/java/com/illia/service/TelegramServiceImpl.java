package com.illia.service;


import com.illia.client.ForecastClient;
import com.illia.client.TelegramClient;
import com.illia.config.TelegramClientConfig;
import com.illia.model.GetUpdatesRequest;
import com.illia.model.GetUpdatesResponse;
import com.illia.model.SendMessageRequest;
import com.illia.service.processor.MessageTextProcessor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Value
@Service
@RequiredArgsConstructor
public class TelegramServiceImpl implements TelegramService {

    TelegramClient client;
    MessageTextProcessor generalMessageTextProcessor;
    LastUpdateIdKeeper idKeeper;


    @Override
    public void getUpdates() {
        try {
            log.info("getting updates :{}", idKeeper.getUpdateId());
            long offset = idKeeper.getUpdateId();
            var response = client.getUpdates(new GetUpdatesRequest(offset));
            log.info("got :{} messages", response.getResponses().size());
            long lastProcessedId = proceedResponse(response);
            idKeeper.setUpdateId(lastProcessedId == 0 ? offset : lastProcessedId + 1);
            log.info("getUpdates end, lastUpdateId:{}", lastProcessedId);
        }
        catch (Exception e){
            log.error("Exception in getUpdates ", e);
        }
    }



    private long proceedResponse(GetUpdatesResponse response){
        var responses = response.getResponses();
        long lastUpdateId = 0l;
        for (GetUpdatesResponse.Response resp : responses) {
            lastUpdateId = Math.max(lastUpdateId, resp.getUpdateId());
            processMessage(resp.getMessage());
        }
        return lastUpdateId;
    }


    private void processMessage(GetUpdatesResponse.Message message) {
        long chatId = message.getChat().getId();
        long messageIdToReply = message.getId();
        String messageText = message.getText().trim();

        generalMessageTextProcessor.process(messageText)
                .doOnNext(reply -> {
                    var result = reply.getFailureReply() == null ? reply.getSuccessReply() : reply.getFailureReply();
                    var sendMessageRequest = new SendMessageRequest(chatId, result, messageIdToReply);
                    client.sendMessage(sendMessageRequest);
                }).subscribe();
    }
}
