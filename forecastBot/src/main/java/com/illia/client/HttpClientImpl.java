package com.illia.client;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class HttpClientImpl implements HttpClient{


    @Override
    public Mono<String> performRequest(String url, String params) {


        WebClient webclient = WebClient.builder().baseUrl(url).build();
        Mono<String> result = webclient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        return result;
    }
}
