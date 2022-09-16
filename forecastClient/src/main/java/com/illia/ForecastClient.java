package com.illia;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ForecastClient {

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(ForecastClient.class).run(args);
    }
}