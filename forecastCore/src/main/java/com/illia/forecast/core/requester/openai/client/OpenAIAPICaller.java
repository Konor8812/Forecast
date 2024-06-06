package com.illia.forecast.core.requester.openai.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.forecast.core.api.openai.request.OpenAIRequestBody;
import com.illia.forecast.core.api.openai.request.template.Templates;
import com.illia.forecast.core.api.openai.response.OpenAIMatchingResponse;
import com.illia.forecast.core.config.OpenAIConfig;
import com.illia.forecast.core.model.ForecastExplanation;
import com.illia.forecast.core.model.WeatherForecast;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OpenAIAPICaller {

    private final OpenAIConfig aiConfig;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Retryable(
            maxAttempts = 2,
            backoff = @Backoff(delay = 1000),
            recover = "recoverFromRequestException"
    )
    public Mono<ForecastExplanation> performAPIRequest(WeatherForecast weatherForecast) {

        var requestBody = new OpenAIRequestBody(aiConfig.getModelName());

        requestBody.addMessage("system", Templates.getSystemPrompt());
        requestBody.addMessage("user", Templates.getUserPrompt(weatherForecast));

        return webClient
                .post()
                .uri(aiConfig.getOpenaiCompletionsUrl())
                .headers(httpHeaders -> {
                    httpHeaders.add("content-type", MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.add("Authorization", String.format("Bearer %s", aiConfig.getApiKey()));
                })
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(OpenAIMatchingResponse.class)
                .map(response -> new ForecastExplanation(response.choices().get(0).message().content()));
    }

    @Recover
    public WeatherForecast recoverFromRequestException(RuntimeException ex,
                                                       WeatherForecast weatherForecast) {
        return WeatherForecast.builder().okStatus(false)
                .errorMessage("Wasn't able to receive response from OpenAI").build();
    }
}