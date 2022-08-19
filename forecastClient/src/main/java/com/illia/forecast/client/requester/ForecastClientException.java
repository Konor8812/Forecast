package com.illia.forecast.client.requester;

public class ForecastClientException extends RuntimeException{
    public ForecastClientException(String message, Throwable ex){
        super(message, ex);
    }
}
