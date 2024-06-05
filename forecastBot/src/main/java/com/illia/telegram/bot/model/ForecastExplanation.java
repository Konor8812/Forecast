package com.illia.telegram.bot.model;

public record ForecastExplanation(String date,
                                  String location,
                                  String forecast_explanation) {
  @Override
  public String toString(){
    return new StringBuilder()
        .append("Forecast for ").append(date).append(" in ").append(location).append(":\n")
        .append(forecast_explanation).toString();
  }
}