package com.illia.forecast.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
