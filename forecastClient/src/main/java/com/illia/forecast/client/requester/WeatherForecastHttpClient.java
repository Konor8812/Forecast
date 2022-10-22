package com.illia.forecast.client.requester;


import com.illia.forecast.core.model.WeatherForecast;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherForecastHttpClient implements HttpClient{

    private final WebClient.Builder webBuilder = WebClient.builder();

    @Override
    public Mono<WeatherForecast> getForecast(String url) {
        var client = webBuilder.baseUrl(url).build();
        try {
            return client
                    .get()
                    .accept(MediaType.ALL)
                    .retrieve()
                    .bodyToMono(WeatherForecast.class);

        } catch (Exception ex) {
            log.error("request error, url:{}", url, ex);
            throw new ForecastClientException(ex);
        }
    }
}
