package com.illia.forecast.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Location {

    private String country;
    private String city;
    private String timeZone;

    private String longitude;
    private String latitude;
    private String altitude;

    public Location(){

    }

    @Override
    public String toString() {
        return city + ", " + country;
    }
}
