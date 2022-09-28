package com.illia.forecast.client.requester;


import reactor.core.publisher.Mono;

public interface HttpClient {
    Mono<String> performRequest(String url);
}
