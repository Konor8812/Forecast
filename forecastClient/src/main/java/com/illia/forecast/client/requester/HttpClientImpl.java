package com.illia.forecast.client.requester;


import com.illia.forecast.core.requester.RequesterException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@Slf4j
public class HttpClientImpl implements HttpClient {


    @Override
    public Mono<String> performRequest(String url) {
        try {
            WebClient client = WebClient.builder().baseUrl(url).build();
            return client.get()
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class);

        } catch (Exception ex) {
            log.error("request error, url:{}", url, ex);
            throw new RequesterException(ex);
        }

    }

}
