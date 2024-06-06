package com.illia.telegram.bot.model;

public record ForecastExplanation(
                                  String forecast_explanation) {
  @Override
  public String toString(){
    return forecast_explanation;
  }
}