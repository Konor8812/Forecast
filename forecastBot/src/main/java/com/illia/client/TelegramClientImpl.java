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
    LastUpdateIdKeeper lastUpdateIdKeeper;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public GetUpdatesResponse getUpdates(GetUpdatesRequest request) {
        long offset = lastUpdateIdKeeper.getUpdateId();

        String urlWithParams = String.format("%s%s%s%s", tconf.getUrl(), tconf.getToken(), "/getUpdates?offset=", request.getOffset());


        var result = client.performRequest(urlWithParams).map(this::parse);

        result.block().getResponses().forEach(System.out::println);
//        var temp = mapper.readValue(result, GetUpdatesResponse.class);
//
//        lastUpdateId = lastUpdateId == 0 ? offset : lastUpdateId + 1;
//        lastUpdateIdKeeper.set(lastUpdateId);

        return null;
    }

    @Override
    public void sendMessage(SendMessageRequest request) {

    }

    private GetUpdatesResponse parse(String valuesToParse) {
        try {
            return objectMapper.readValue(valuesToParse, GetUpdatesResponse.class);
        } catch (JsonProcessingException e) {
            log.error("Unable to parse string " + valuesToParse, e);
            throw new GetUpdateException(e.getMessage());
        }
    }
}
