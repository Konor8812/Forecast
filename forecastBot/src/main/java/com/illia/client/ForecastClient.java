package com.illia.client;


public interface ForecastClient {
    String getForecast(String url, int numberOfDays, double latitude, double longitude);
}
