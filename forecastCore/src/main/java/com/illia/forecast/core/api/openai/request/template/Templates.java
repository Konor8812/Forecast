package com.illia.forecast.core.api.openai.request.template;

import com.illia.forecast.core.model.WeatherForecast;

public class Templates {

  private static final String systemPrompt = "You are an intelligent assistant designed to analyze and summarize weather forecasts. You will receive weather forecast data for multiple days. Your task is to generate a clear and concise explanation of the weather for each day in a specific location, including the date, location, and an overall summary of the forecast. Each day consists of 8 weather objects representing 3-hour intervals. If you meet `unknown` as value for location, you can output `unknown` as a result in format requested";
  private static final String userPrompt = "Here is the weather forecast data for the next few days. Please provide a summary for each day, including the date, location, and an overall forecast explanation.";

  public static String getSystemPrompt() {
    return systemPrompt;
  }

  public static String getUserPrompt(WeatherForecast weatherForecast) {
    return userPrompt + weatherForecast.getForecastFor2Days();
  }
}
