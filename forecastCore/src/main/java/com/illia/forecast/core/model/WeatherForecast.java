package com.illia.forecast.core.model;

import lombok.Data;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

@Data
public class WeatherForecast {

    private Location location;
    private String sunRiseTime;
    private String sunSetTime;
    private List<Weather> weatherList;


    private static final String DATE_FORMAT = "dd.MM.yyyy HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    public WeatherForecast() {
        weatherList = new ArrayList<>();
    }

    public void addWeatherForecast(Weather weather) {
        weatherList.add(weather);
    }

    @Override
    public String toString() {

        return "WeatherForecast{" +
                "location=" + location +
                ", sunRiseTime='" + sunRiseTime + '\'' +
                ", sunSetTime='" + sunSetTime + '\'' +
                ", weatherList=" + weatherList +
                '}';
    }

    public String getForecastForTimePeriod(int days) {
        if (days > 5) {
            days = 5;
        } else if (days <= 0) {
            days = 1;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Weather forecast for ").append(location.toString()).append(" on ").append(ZonedDateTime.now().format(DATE_TIME_FORMATTER)).append("\n")
                .append("Sun rises at ").append(sunRiseTime).append("\nSets down at ").append(sunSetTime).append("\n\n");
        for (int i = 0; i < days * 8; i++) {
            Weather weather = weatherList.get(i);
            sb.append(weather.getTime())
                    .append("\nOverall state: ").append(weather.getGeneralState())
                    .append("\nTemperature: ").append(weather.getTemperature())
                    .append("°C, feels like ").append(weather.getTemperatureFeelsLike())
                    .append("°C\nPrecipitation probability: ").append(weather.getPrecipitationProbability())
                    .append("\nHumidity: ").append(weather.getHumidity())
                    .append("%\nWind speed: ").append(weather.getWindSpeed()).append(" mps (")
                    .append(DECIMAL_FORMAT.format(Double.parseDouble(weather.getWindSpeed()) * 3.6)).append(" km/h)\n\n");
        }
        return sb.toString().replaceAll("\n", System.lineSeparator());
    }

}
