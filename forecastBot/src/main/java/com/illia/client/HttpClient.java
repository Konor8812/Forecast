package com.illia.client;

import reactor.core.publisher.Mono;

public interface HttpClient {
    Mono<String> performRequest(String url, String params);

}
