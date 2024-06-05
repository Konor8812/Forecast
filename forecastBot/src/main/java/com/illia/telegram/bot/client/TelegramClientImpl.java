package com.illia.telegram.bot.client;


import com.illia.telegram.bot.config.TelegramClientConfig;
import com.illia.telegram.bot.model.GetUpdatesRequest;
import com.illia.telegram.bot.model.GetUpdatesResponse;
import com.illia.telegram.bot.model.SendMessageRequest;
import coresearch.cvurl.io.request.CVurl;
import lombok.RequiredArgsConstructor;
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
    var url = createGetUpdatesRequestUrl(telegramClientConfig.getGetUpdatesOperationUrl(), request);

    return webClient.get()
        .uri(url)
        .retrieve()
        .bodyToMono(GetUpdatesResponse.class);
  }

  @Override
  public void sendMessage(SendMessageRequest request) {
    var url = createSendMessageRequestUrl(telegramClientConfig.getSendMessageOperationUrl(),
        request);

    cVurl.post(url)
        .asObject(Void.class);
  }

  private String createGetUpdatesRequestUrl(String operationUrl, GetUpdatesRequest request) {
    var queryParams = new LinkedMultiValueMap<String, String>();
    queryParams.add("offset", String.valueOf(request.offset()));

    String baseUrl = String.format("%s%s", telegramClientConfig.getUrl(),
        telegramClientConfig.getToken());
    return UriComponentsBuilder.fromHttpUrl(baseUrl + operationUrl)
        .queryParams(queryParams)
        .toUriString();
  }

  private String createSendMessageRequestUrl(String operationUrl, SendMessageRequest request) {
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.add("chat_id", String.valueOf(request.getChatId()));
    queryParams.add("text", request.getText());
    queryParams.add("reply_to_message_id", String.valueOf(request.getReplyToMessageId()));

    String baseUrl = String.format("%s%s", telegramClientConfig.getUrl(),
        telegramClientConfig.getToken());
    return UriComponentsBuilder.fromHttpUrl(baseUrl + operationUrl)
        .queryParams(queryParams)
        .toUriString();
  }
}
