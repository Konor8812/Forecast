package com.illia.client;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.config.TelegramClientConfig;
import com.illia.model.GetUpdatesRequest;
import com.illia.model.GetUpdatesResponse;
import com.illia.model.SendMessageRequest;
import com.illia.service.LastUpdateIdKeeper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class TelegramClientImpl implements TelegramClient {

    @Autowired
    TelegramClientConfig tconf;

    @Autowired
    HttpClient client;

    @Autowired
    HttpClient forecastClient;

    @Autowired
    LastUpdateIdKeeper lastUpdateIdKeeper;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public GetUpdatesResponse getUpdates(GetUpdatesRequest request) {
        try {
            String params = objectMapper.writeValueAsString(request);
            String url = createUrl("/getUpdates?");

            var result = client.performRequest(url, params);
            return this.parse(result);
        } catch (Exception e) {
            log.error("Exception while getting updates", e);
            e.printStackTrace();
            throw new GetUpdateException(e.getMessage());
        }
    }

    @Override
    public void sendMessage(SendMessageRequest request) {
        try {
            String url = createUrl("/sendMessage?");
            String params = objectMapper.writeValueAsString(request);
//            String params = String.format("%s%s%s%s%s%s", "/sendMessage?chat_id=", request.getChatId(), "&text=", request.getText(), "&reply_to_message_id=", request.getReplyToMessageId());
            client.performRequest(url, params);
        } catch (Exception e) {
            log.error("Exception while sending message ", e);
        }
    }

    private GetUpdatesResponse parse(String valuesToParse) {
        try {
            return objectMapper.readValue(valuesToParse, GetUpdatesResponse.class);
        } catch (JsonProcessingException e) {
            log.error("Unable to parse string " + valuesToParse, e);
            throw new GetUpdateException(e.getMessage());
        }
    }

    private String createUrl(String requestMethod) {
        return String.format("%s%s%s", tconf.getUrl(), tconf.getToken(), requestMethod);
    }
}
