package com.illia.forecast.client.requester;

public class ForecastClientException extends RuntimeException{
    public ForecastClientException( Throwable ex){
        super(ex);
    }
}
