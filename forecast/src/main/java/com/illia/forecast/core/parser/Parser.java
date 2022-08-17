package com.illia.forecast.core.parser;


import com.illia.forecast.core.model.WeatherForecast;

public interface Parser {

    public WeatherForecast parse(String content);
}
