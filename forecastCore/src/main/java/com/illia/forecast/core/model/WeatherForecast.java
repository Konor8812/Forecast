package com.illia.forecast.core.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class WeatherForecast {

    private Location location;
    private String sunRiseTime;
    private String sunSetTime;
    private List<Weather> weatherList;

    public WeatherForecast() {
        weatherList = new ArrayList<>();
    }

    public void addWeatherForecast( Weather weather) {
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

    public String getForecastForTimePeriod(int days){
        if(days > 5){
            days = 5;
        }else if(days <=0){
            days = 1;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Weather forecast for ").append(location).append(" on ").append(LocalDateTime.now()).append(" :\n")
                .append("sun rises at ").append(sunRiseTime).append(", sets down at ").append(sunSetTime).append("\n");
        for(int i = 0; i < days * 8; i++){
            Weather weather = weatherList.get(i);
            sb.append(weather.getTime()).append("\nOverall state: ")
                    .append(weather.getGeneralState()).append("\nTemperature: ")
                    .append(weather.getTemperature()).append("°C, feels like ")
                    .append(weather.getTemperatureFeelsLike()).append("°C\nPrecipitation probability: ")
                    .append(weather.getPrecipitationProbability()).append("\nHumidity: ")
                    .append(weather.getHumidity()).append("%\nWind speed: ")
                    .append(weather.getWindSpeed()).append(" mps\nPressure: ")
                    .append(weather.getPressure()).append(" hPa\n\n");
        }

        return sb.toString();
    }
}
