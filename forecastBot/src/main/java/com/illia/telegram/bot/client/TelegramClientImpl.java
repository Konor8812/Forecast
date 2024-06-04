package com.illia.telegram.bot.client;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.telegram.bot.config.TelegramClientConfig;
import com.illia.telegram.bot.model.GetUpdatesRequest;
import com.illia.telegram.bot.model.GetUpdatesResponse;
import com.illia.telegram.bot.model.SendMessageRequest;
import coresearch.cvurl.io.request.CVurl;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodySubscriber;
import java.net.http.HttpResponse.ResponseInfo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TelegramClientImpl implements TelegramClient {

    private final TelegramClientConfig telegramClientConfig;

    private final WebClient webClient;
    private final CVurl cVurl;

    @Override
    public Mono<GetUpdatesResponse> getUpdates(GetUpdatesRequest request) {
        var url = createRequestUrl(telegramClientConfig.getGetUpdatesOperationUrl());

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(GetUpdatesResponse.class);
    }

    @Override
    public void sendMessage(SendMessageRequest request) {
        var url = createRequestUrl(telegramClientConfig.getSendMessageOperationUrl(), request);

        cVurl.post(url)
            .asObject();
    }

    // todo: to refactor prettily
    private String createRequestUrl(String operationUrl) {
        return String.format("%s%s%s", telegramClientConfig.getUrl(), telegramClientConfig.getToken(), operationUrl);
    }

    private String createRequestUrl(String operationUrl, SendMessageRequest request) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("chat_id", String.valueOf(request.getChatId()));
        queryParams.add("text", request.getText());
        queryParams.add("reply_to_message_id", String.valueOf(request.getReplyToMessageId()));

        String baseUrl = String.format("%s%s", telegramClientConfig.getUrl(), telegramClientConfig.getToken());
        return UriComponentsBuilder.fromHttpUrl(baseUrl + operationUrl)
            .queryParams(queryParams)
            .toUriString();
    }
}
