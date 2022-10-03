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
    private String windSpeed;
    private String temperature;
    private String temperatureFeelsLike;
    private String pressure;
    private String humidity;

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
