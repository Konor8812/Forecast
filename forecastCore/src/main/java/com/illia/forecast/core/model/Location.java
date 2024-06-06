package com.illia.forecast.core.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    private String country;
    private String city;

    @Override
    public String toString() {

        if (city.isEmpty() || country.isEmpty()) {
            return "Undefined";
        }
        return city + ", " + country;
    }

}
