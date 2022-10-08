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
    public String getLocationAsString(){
        StringBuilder sb = new StringBuilder();
        sb.append(city).append(", ").append(country).append(", timezone: ").append(timeZone);
        return sb.toString();
    }

    public String getGeographicalCoordinatesAsString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Coordinates: latitude = ").append(latitude).append(", longitude = ").append(longitude).append(", altitude = ").append(altitude);
        return sb.toString();

    }

    @Override
    public String toString() {
        return city + ", " + country;
    }
}
