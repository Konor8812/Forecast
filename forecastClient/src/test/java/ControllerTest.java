import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.forecast.client.requester.ForecastRequester;
import com.illia.forecast.core.model.WeatherForecast;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    ForecastRequester requester;

    @Test
    @Ignore
    public void test() throws IOException {
        double latitude = 50.523;
        double longitude = 30.24;


        String url = String.format("%s/%s/%s", "http://localhost:8080/api/forecast", latitude, longitude);
        System.out.println(url);
//        when(requester.getForecast(url))
//            .thenReturn(Mono.just(new ObjectMapper().readValue(new File("provided.json"), WeatherForecast.class)));

        System.out.println(requester.getForecast(url));
    }


//    var type = "CBR";
//    var currency = "EUR";
//    var date = "02-03-2021";
//
//    var url = String.format("%s/%s/%s", config.getUrl(), currency, date);
//    when(httpClient.performRequest(url))
//            .thenReturn(Mono.just("{\"numCode\":\"978\",\"charCode\":\"EUR\",\"nominal\":\"1\",\"name\":\"Евро\",\"value\":\"89,4461\"}"));
//    //when
//
//    var result = webTestClient
//            .get().uri(String.format("/api/v1/currencyRate/%s/%s/%s", type, currency, date))
//            .accept(MediaType.APPLICATION_JSON)
//            .exchange()
//            .expectStatus().isOk()
//            .returnResult(String.class)
//            .getResponseBody()
//            .blockLast();
//
//    //then
//    assertThat(result).isEqualTo("{\"charCode\":\"EUR\",\"nominal\":\"1\",\"value\":\"89,4461\"}");
}
