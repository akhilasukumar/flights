package nl.abnamro.flight.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FlightDetailsControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void happyflow() throws URISyntaxException {

        final String baseUrl = "https://localhost:9082/flights";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity<>(headers);

        // URI (URL) parameters
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("origin", "BOM");
        urlParams.put("destination", "DEL");

        // Query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl)
        // Add query parameter
        .queryParam("price", "100");

        restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, requestEntity, class_p);

        return response.getBody();
    }
}



    }
    }
