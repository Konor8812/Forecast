import com.illia.forecast.core.model.WeatherForecast;
import com.illia.forecast.core.parser.Parser;
import com.illia.forecast.core.parser.WeatherXMLParser;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParserTest {

    @Test
    public void parserTest() throws IOException, URISyntaxException {

        URI uri = ClassLoader.getSystemResource("xmlResponse.xml").toURI();
        String weatherXML = Files.readString(Paths.get(uri), Charset.forName("Windows-1251"));
        Parser parser = new WeatherXMLParser();

        WeatherForecast forecast = parser.parse(weatherXML);

        assertNotNull(forecast.getLocation().getCity());
        assertNotNull(forecast.getLocation().getLatitude());

        assertNotNull(forecast.getSunSetTime());
        assertNotNull(forecast.getForecastForTimePeriod(1));

        Assertions.assertEquals(40, forecast.getWeatherList().size());

    }

}
