package com.illia.forecast.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class WeatherForecast {

    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            DATE_FORMAT);
    private boolean okStatus;
    private String errorMessage;
    private Location location;
    private String sunRiseTime;
    private String sunSetTime;
    private List<Weather> weatherList;

    public WeatherForecast() {
        weatherList = new ArrayList<>();
    }

    public WeatherForecast(String errorMessage) {
        this.errorMessage = errorMessage;
        okStatus = false;
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

    public WeatherForecast getForecastForNDays(Short daysAmount) {
        if (daysAmount < 1) {
            daysAmount = 1;
        }
        if (daysAmount <= 4) {
            this.weatherList = weatherList.subList(0, weatherList.size() * daysAmount / 5);
        }

        return this;
    }

    public String getFormattedForecast() {
        StringBuilder sb = new StringBuilder();
        sb.append("Weather forecast for ").append(location.toString()).append(" on ")
                .append(LocalDate.now().format(DATE_TIME_FORMATTER)).append("\n")
                .append("Sun rises at ").append(sunRiseTime).append("\nSets down at ").append(sunSetTime)
                .append("\n\n");
        for (var w : weatherList) {
            sb.append(w.getTime())
                    .append("\nOverall state: ").append(w.getGeneralState())
                    .append(", temperature feels like ").append(w.getTemperatureFeelsLike())
                    .append("°C\nPrecipitation probability: ").append(w.getPrecipitationProbability())
                    .append("\n");
        }
        return sb.toString().replaceAll("\n", System.lineSeparator());
    }

}
