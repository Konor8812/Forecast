package com.illia.telegram.bot.service;


import com.illia.telegram.bot.client.TelegramClient;
import com.illia.telegram.bot.model.GetUpdatesRequest;
import com.illia.telegram.bot.model.GetUpdatesResponse;
import com.illia.telegram.bot.model.SendMessageRequest;
import com.illia.telegram.bot.service.processor.MessageTextProcessor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
                    if(result.length() > 4000){
                        var sendMessagePart1 = new SendMessageRequest(chatId, result.substring(0, result.length() / 2), messageIdToReply);
                        var sendMessagePart2 = new SendMessageRequest(chatId, result.substring(result.length() / 2), messageIdToReply);
                        client.sendMessage(sendMessagePart1);
                        client.sendMessage(sendMessagePart2);
                    }else {
                        var sendMessageRequest = new SendMessageRequest(chatId, result, messageIdToReply);
                        client.sendMessage(sendMessageRequest);
                    }
                }).subscribe();
    }
}
