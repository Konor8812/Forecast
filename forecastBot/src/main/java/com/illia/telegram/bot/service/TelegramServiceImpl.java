package com.illia.telegram.bot.service;


import com.illia.telegram.bot.client.TelegramClient;
import com.illia.telegram.bot.model.GetUpdatesRequest;
import com.illia.telegram.bot.model.GetUpdatesResponse;
import com.illia.telegram.bot.model.SendMessageRequest;
import com.illia.telegram.bot.service.processor.GeneralMessageTextProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramServiceImpl implements TelegramService {

    private final TelegramClient client;
    private final GeneralMessageTextProcessor generalMessageTextProcessor;
    private final LastUpdateIdKeeper idKeeper;


    @Override
    public void getUpdates() {
        long offset = idKeeper.getUpdateId();
        client.getUpdates(new GetUpdatesRequest(offset))
                .doOnNext(resp -> {
                    long lastProcessedId = proceedResponse(resp);
                    idKeeper.setUpdateId(lastProcessedId == 0 ? offset : lastProcessedId + 1);
                    log.info("getUpdates end, lastUpdateId:{}", lastProcessedId);
                })
                .subscribe();
    }


    private long proceedResponse(GetUpdatesResponse response) {
        var responses = response.getResponses();
        long lastUpdateId = 0L;
        for (var resp : responses) {
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
                    var result = reply.failureReply() == null ? reply.successReply() : reply.failureReply();
                    var sendMessageRequest = new SendMessageRequest(chatId, result, messageIdToReply);
                  System.out.println(sendMessageRequest);
                    client.sendMessage(sendMessageRequest);
                }).subscribe();
    }
}
