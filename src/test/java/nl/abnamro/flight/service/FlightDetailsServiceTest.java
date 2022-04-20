package nl.abnamro.flight.service;

import nl.abnamro.flight.model.FlightDetails;
import nl.abnamro.flight.repository.FlightDetailsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightDetailsServiceTest {

    @Mock
    private FlightDetailsRepository flightDetailsRepository;

    @Autowired
    private FlightDetailsService flightDetailsService;

    private final List<FlightDetails> flightDetailList = new ArrayList<>();
    private Map<String, String> filteringParams = new HashMap<>();

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        flightDetailList.add(new FlightDetails("A101", "BOM", "DEL", LocalTime.of(10, 0), LocalTime.of(17, 0), 80));
        flightDetailList.add(new FlightDetails("B101", "BOM", "DEL", LocalTime.of(12, 0), LocalTime.of(21, 0), 100));
        flightDetailList.add(new FlightDetails("C101", "BOM", "DEL", LocalTime.of(9, 0), LocalTime.of(15, 0), 50));

        Mockito.when(flightDetailsRepository.findByOriginAndDestination(anyString(), anyString())).thenReturn(flightDetailList);
        flightDetailsService = new FlightDetailsService(flightDetailsRepository);
    }

    @AfterEach
    public void clear() {
        filteringParams.clear();
    }

    @Test
    public void test_givenNoRequestParams_thenRetrieveAll() {
        List<FlightDetails> flightDetails = flightDetailsService.shortListFlightDetails("BOM", "DEL", filteringParams);
        assertThat(flightDetails).isNotNull();
        assertThat(flightDetails.size()).isEqualTo(3);
    }

    @Test
    public void test_givenPriceRequestParam_thenRetrieveFilteredPrice() {
        filteringParams.put("price", "90");
        List<FlightDetails> flightDetails = flightDetailsService.shortListFlightDetails("BOM", "DEL", filteringParams);
        assertThat(flightDetails).isNotNull();
        assertThat(flightDetails.size()).isEqualTo(2);

    }

    @Test
    public void test_givenArrivalTimeRequestParams_thenRetrieveFilteredArrivalTime() {
        filteringParams.put("startArrivalTime", "14:30");
        filteringParams.put("endArrivalTime", "16:30");
        List<FlightDetails> flightDetails = flightDetailsService.shortListFlightDetails("BOM", "DEL", filteringParams);
        assertThat(flightDetails).isNotNull();
        assertThat(flightDetails.size()).isEqualTo(1);
    }

    @Test
    public void test_givenMultipleParams_thenRetrieveFilteredList() {
        filteringParams.put("startDepartureTime", "09:00");
        filteringParams.put("travelTime", "8");
        List<FlightDetails> flightDetails = flightDetailsService.shortListFlightDetails("BOM", "DEL", filteringParams);
        assertThat(flightDetails).isNotNull();
        assertThat(flightDetails.size()).isEqualTo(1);

    }

}
