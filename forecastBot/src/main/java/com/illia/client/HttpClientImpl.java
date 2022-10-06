package com.illia.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class HttpClientImpl implements HttpClient{

    @Override
    public String performRequest(String url, String params) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(params))
                .build();
        return doRequest(request);
    }

    private String doRequest(HttpRequest request){
        try{
            java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            log.error("send http request error :{} ", request.uri());
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            throw new HttpClientException(e.getMessage());
        }
    }
}
