package com.illia.forecast.client.api;

import com.illia.ForecastClient;
import com.illia.forecast.client.model.ForecastExplanation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;


@ContextConfiguration(classes = ForecastClient.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndpointControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Disabled
    public void getForecastConnectionTest() {
        String url = String.format("%s/%s/%s", "http://localhost:8081/home/forecast", 10, 10);
        webTestClient
                .get().uri(url)
                .accept(MediaType.ALL)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ForecastExplanation.class);
    }
}
