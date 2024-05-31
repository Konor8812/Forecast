package com.illia.forecast.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

  private String time;
  private String generalState;
  private String precipitationProbability;
  private String temperatureFeelsLike;

  @Override
  public String toString() {
    return new StringBuilder()
        .append("time: ").append(time)
        .append("| general state: ").append(generalState)
        .append("| precipitationProbability: ").append(precipitationProbability)
        .append("| temperature feels like: ").append(temperatureFeelsLike).toString();
  }
}
