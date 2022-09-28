package com.illia.forecast.core.parser;


import com.illia.forecast.core.model.WeatherForecast;

public interface Parser {

    WeatherForecast parse(String content);
}
