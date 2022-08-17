package com.illia.forecast.core.service;


import com.illia.forecast.core.config.WeatherForecastConfig;
import com.illia.forecast.core.model.WeatherForecast;
import com.illia.forecast.core.parser.Parser;
import com.illia.forecast.core.requester.WeatherForecastRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    private WeatherForecastConfig weatherForecastConfig;
    @Autowired
    private WeatherForecastRequester forecastRequester;

    @Autowired
    private Parser parser;

    public String getParsedWeather(double latitude, double longitude){

        String urlWithParams = String.format("%s?lat=%f&lon=%f&appid=%s&mode=%s", weatherForecastConfig.getBaseUrl(), latitude, longitude, weatherForecastConfig.getAppid(), weatherForecastConfig.getMode());
        WeatherForecast weatherForecast = parser.parse(forecastRequester.requestForecastAsXML(urlWithParams));

        return null;
    }


    //api.openweathermap.org/data/2.5/forecast?lat=50.523000&lon=30.240000&appid=23b08b8bb2b1baec815f8e52eb970516&mode=xml
//http://api.openweathermap.org/data/2.5/forecast?lat=50.523&lon=30.24&appid=23b08b8bb2b1baec815f8e52eb970516&mode=xml

//    var urlWithParams = String.format("%s?date_req=%s", cbrConfig.getUrl(), DATE_FORMATTER.format(date));
//    var ratesAsXml = cbrRequester.getRatesAsXml(urlWithParams);
}
