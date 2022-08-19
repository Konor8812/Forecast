package com.illia;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ForecastCore {

    public static void main(String[] args) {
        //SpringApplication.run(ForecastClient.class, args);
        new SpringApplicationBuilder().sources(ForecastCore.class).run(args);
    }
}