package com.illia.forecast.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Weather {
    private String generalState;
    private String precipitationProbability;
    private String windSpeed;
    private String temperature;
    private String temperatureFeelsLike;
    private String pressure;
    private String humidity;
    private String time;

    @Override
    public String toString() {
        return "Weather{" +
                "generalState='" + generalState + '\'' +
                ", precipitationProbability='" + precipitationProbability + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", temperature='" + temperature + '\'' +
                ", temperatureFeelsLike='" + temperatureFeelsLike + '\'' +
                ", pressure='" + pressure + '\'' +
                ", humidity='" + humidity + '\'' +
                '}';
    }
}
