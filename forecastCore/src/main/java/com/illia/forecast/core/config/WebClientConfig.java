package com.illia.forecast.core.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClientCustomizer webClientCustomizer(ObjectMapper objectMapper) {
        return builder -> {
            builder.exchangeStrategies(ExchangeStrategies.builder()
                    .codecs(configurer -> {
                        configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
                        configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
                    })
                    .build());
        };
    }

    @Bean
    public WebClient webClient(@Qualifier("webClientCustomizer") WebClientCustomizer customizer) {
        WebClient.Builder builder = WebClient.builder();
        customizer.customize(builder);
        return builder.build();
    }
}