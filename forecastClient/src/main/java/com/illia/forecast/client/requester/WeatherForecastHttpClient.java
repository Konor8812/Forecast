package com.illia.forecast.client.requester;


import com.illia.forecast.client.model.ForecastExplanation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WeatherForecastHttpClient implements HttpClient {

  private final WebClient.Builder webBuilder = WebClient.builder();

  @Override
  public Mono<ForecastExplanation> getForecast(String url) {
    var client = webBuilder.baseUrl(url).build();
    try {
      return client
          .get()
          .accept(MediaType.ALL)
          .retrieve()
          .bodyToMono(ForecastExplanation.class);

    } catch (Exception ex) {
      throw new ForecastClientException(ex);
    }
  }
}
