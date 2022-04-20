package nl.abnamro.flight.repository;

import nl.abnamro.flight.model.FlightDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FlightDetailsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FlightDetailsRepository flightDetailsRepository;

    @Test
    public void test_givenOriginAndDestination_retrieveAll() {
        FlightDetails fd = new FlightDetails("A101", "BOM", "DEL",
        LocalTime.of(11, 00), LocalTime.of(17, 00), 80);
        FlightDetails fd1 = new FlightDetails("A102", "BOM", "DEL",
        LocalTime.of(10, 00), LocalTime.of(19, 00), 100);

        entityManager.persist(fd);
        entityManager.persist(fd1);

        List<FlightDetails> flightDetails = flightDetailsRepository.findByOriginAndDestination("BOM", "DEL");

        assertThat(flightDetails).isNotNull();
        assertThat(flightDetails.size()).isEqualTo(2);

    }
}
