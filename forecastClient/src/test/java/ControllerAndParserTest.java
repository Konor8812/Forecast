import com.illia.forecast.client.requester.WeatherForecastRequester;
import com.illia.forecast.core.parser.WeatherXMLParser;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerAndParserTest {

    @Autowired
    WebTestClient webTestClient;

    WeatherForecastRequester requester = Mockito.mock(WeatherForecastRequester.class);

    @Test
    @Ignore
    public void getForecastTest() throws IOException, URISyntaxException {
        double latitude = 50.523;
        double longitude = 30.24;

        URI uri = ClassLoader.getSystemResource("provided.xml").toURI();

        String forecastAsString = Files.readString(Paths.get(uri), Charset.forName("Windows-1251"));
        when(requester.getForecast(any()))
                .thenReturn(Mono.just(
                        new WeatherXMLParser().parse(forecastAsString)));


        String url = String.format("%s/%s/%s/%s", "http://localhost:8082/api/forecast", 1, latitude, longitude);


        System.out.println(requester.getForecast(url).block());


    }

}
