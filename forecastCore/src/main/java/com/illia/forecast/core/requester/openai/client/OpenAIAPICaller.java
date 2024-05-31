package com.illia.forecast.core.requester.openai.client;

import com.illia.forecast.core.api.openai.request.OpenAIRequestBody;
import com.illia.forecast.core.api.openai.request.template.Templates;
import com.illia.forecast.core.api.openai.response.OpenAIMatchingResponse;
import com.illia.forecast.core.config.OpenAIConfig;
import com.illia.forecast.core.model.ForecastExplanation;
import com.illia.forecast.core.model.WeatherForecast;
import com.illia.forecast.core.requester.openai.tools.ExplainForecastTool;
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

  @Retryable(
      maxAttempts = 2,
      backoff = @Backoff(delay = 1000),
      recover = "recoverFromRequestException"
  )
  public Mono<ForecastExplanation> performAPIRequest(WeatherForecast weatherForecast) {

    var requestBody = new OpenAIRequestBody(aiConfig.getModelName(), "explain_forecast",
        aiConfig.getMaxTokens());

    requestBody.addMessage("system", Templates.getSystemPrompt());
    requestBody.addMessage("user", Templates.getUserPrompt(weatherForecast));

    requestBody.addTool(new ExplainForecastTool());

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
        .map(response -> {
          var functionCallArguments = response.choices().get(0)
              .message().tool_calls().get(0)
              .function().arguments();

          return ForecastExplanation.builder()
              .location(functionCallArguments.get("location"))
              .date(functionCallArguments.get("date"))
              .forecast_explanation(functionCallArguments.get("explanation"))
              .build();
        });
  }

  @Recover
  public WeatherForecast recoverFromRequestException(RuntimeException ex,
      WeatherForecast weatherForecast) {
    return WeatherForecast.builder().okStatus(false)
        .errorMessage("Wasn't able to receive response from OpenAI").build();
  }
}