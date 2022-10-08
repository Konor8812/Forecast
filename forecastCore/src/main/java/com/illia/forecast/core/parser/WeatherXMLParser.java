package com.illia.forecast.core.parser;


import com.illia.forecast.core.model.Location;
import com.illia.forecast.core.model.Weather;
import com.illia.forecast.core.model.WeatherForecast;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class WeatherXMLParser implements Parser {

    @Override
    public WeatherForecast parse(String content) {
        WeatherForecast result = new WeatherForecast();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            try (StringReader reader = new StringReader(content)) {
                Document document = db.parse(new InputSource(reader));
                document.getDocumentElement().normalize();

                NamedNodeMap coordinatesNamedNodeMap = document.getElementsByTagName("location").item(1).getAttributes();

                Location location = Location.builder().country(document.getElementsByTagName("country").item(0).getTextContent())
                        .city(document.getElementsByTagName("name").item(0).getTextContent())
                        .timeZone(document.getElementsByTagName("timezone").item(0).getTextContent())
                        .altitude(coordinatesNamedNodeMap.getNamedItem("altitude").getTextContent())
                        .latitude(coordinatesNamedNodeMap.getNamedItem("latitude").getTextContent())
                        .longitude(coordinatesNamedNodeMap.getNamedItem("longitude").getTextContent()).build();

                NamedNodeMap sunInfo = document.getElementsByTagName("sun").item(0).getAttributes();
                Pattern sunTimePattern = Pattern.compile("(\\d\\d:\\d\\d:\\d\\d)");

                Matcher sunRiseTimeMatcher = sunTimePattern.matcher(sunInfo.getNamedItem("rise").getTextContent());
                Matcher sunSetTimeMatcher = sunTimePattern.matcher(sunInfo.getNamedItem("set").getTextContent());
                sunRiseTimeMatcher.find();
                sunSetTimeMatcher.find();
                String sunRiseTime = sunRiseTimeMatcher.group(0);
                String sunSetTime = sunSetTimeMatcher.group(0);

                result.setLocation(location);
                result.setSunRiseTime(sunRiseTime);
                result.setSunSetTime(sunSetTime);

                NodeList nodes = document.getElementsByTagName("time");
                for (int i = 0; i < nodes.getLength(); i++) {
                    NamedNodeMap timeNodes = nodes.item(i).getAttributes();
                    StringBuilder timeBuilder = new StringBuilder();
                    timeBuilder.append("From ")
                            .append(timeNodes.getNamedItem("from").getTextContent())
                            .append(" to ")
                            .append(timeNodes.getNamedItem("to").getTextContent()).append(":");
                    String time = timeBuilder.toString();
                    Element element = (Element) nodes.item(i);

                    NamedNodeMap temperatureElement = element.getElementsByTagName("temperature").item(0).getAttributes();
                    double min = Double.parseDouble(temperatureElement.getNamedItem("min").getTextContent());
                    double max = Double.parseDouble(temperatureElement.getNamedItem("max").getTextContent());
                    double temperature = (min + max) / 2 - 273.15;
                    double temperatureFeelsLike = Double.parseDouble(element.getElementsByTagName("feels_like").item(0).getAttributes().getNamedItem("value").getTextContent()) - 273.15;

                    Weather weather = Weather.builder().generalState(element.getElementsByTagName("symbol").item(0).getAttributes().getNamedItem("name").getTextContent())
                            .precipitationProbability(element.getElementsByTagName("precipitation").item(0).getAttributes().getNamedItem("probability").getTextContent())
                            .humidity(element.getElementsByTagName("humidity").item(0).getAttributes().getNamedItem("value").getTextContent())
                            .pressure(element.getElementsByTagName("pressure").item(0).getAttributes().getNamedItem("value").getTextContent())
                            .temperature(Double.toString(Math.floor(temperature)))
                            .temperatureFeelsLike(Double.toString(Math.round(temperatureFeelsLike)))
                            .windSpeed(element.getElementsByTagName("windSpeed").item(0).getAttributes().getNamedItem("mps").getTextContent())
                            .time(time.replaceAll("T", " "))
                            .build();

                    result.addWeatherForecast(weather);
                }
                return result;
            }

        } catch (Exception ex) {
            log.error("could not parse forecast xml :{}", "content", ex);
            throw new ParserException(ex);
        }
    }
}
