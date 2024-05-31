package com.illia.forecast.core.model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class WeatherForecast {

  private boolean okStatus;
  private String errorMessage;

  private Location location;
  private String sunRiseTime;
  private String sunSetTime;

  private List<Weather> weatherList;


  private static final String DATE_FORMAT = "dd.MM.yyyy";
  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
      DATE_FORMAT);
  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

  public WeatherForecast() {
    weatherList = new ArrayList<>();
  }

  public void addWeatherForecast(Weather weather) {
    weatherList.add(weather);
  }

  @Override
  public String toString() {

    return new StringBuilder()
        .append("location: ").append(location)
        .append("| sunRiseTime: ").append(sunRiseTime)
        .append("| sunSetTime: ").append(sunSetTime)
        .append("| weatherList: ").append(weatherList).toString();
  }

  public WeatherForecast(String errorMessage) {
    this.errorMessage = errorMessage;
    okStatus = false;
  }


  public String getForecastFor2Days() {
    var wl = weatherList.subList(0, weatherList.size() * 2 / 5);
    return new StringBuilder()
        .append("location: ").append(location)
        .append("| sunRiseTime: ").append(sunRiseTime)
        .append("| sunSetTime: ").append(sunSetTime)
        .append("| weatherList: ").append(wl).toString();
  }

  public String getForecastForTimePeriod(int days) {

    if (!okStatus) {
      return errorMessage;
    }

    if (days > 5) {
      days = 5;
    } else if (days <= 0) {
      days = 1;
    }

    StringBuilder sb = new StringBuilder();
    sb.append("Weather forecast for ").append(location.toString()).append(" on ")
        .append(LocalDate.now().format(DATE_TIME_FORMATTER)).append("\n")
        .append("Sun rises at ").append(sunRiseTime).append("\nSets down at ").append(sunSetTime)
        .append("\n\n");
    for (int i = 0; i < days * 8; i++) {
      Weather weather = weatherList.get(i);
      sb.append(weather.getTime())
          .append("\nOverall state: ").append(weather.getGeneralState())
          .append("°C, feels like ").append(weather.getTemperatureFeelsLike())
          .append("°C\nPrecipitation probability: ").append(weather.getPrecipitationProbability());
    }
    return sb.toString().replaceAll("\n", System.lineSeparator());
  }
}
