package com.illia.telegram.bot.client;


import com.illia.telegram.bot.config.TelegramClientConfig;
import com.illia.telegram.bot.model.GetUpdatesRequest;
import com.illia.telegram.bot.model.GetUpdatesResponse;
import com.illia.telegram.bot.model.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TelegramClientImpl implements TelegramClient {

    private final TelegramClientConfig telegramClientConfig;

    private final WebClient webClient;


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
        var url = createRequestUrl(telegramClientConfig.getGetUpdatesOperationUrl());

        webClient.post()
                .uri(url)
                .bodyValue(request)
                .retrieve();
    }

    private String createRequestUrl(String operationUrl) {
        return String.format("%s%s%s", telegramClientConfig.getUrl(), telegramClientConfig.getToken(), operationUrl);
    }
}
