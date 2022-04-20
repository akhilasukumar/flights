package nl.abnamro.flight.repository;

import nl.abnamro.flight.model.FlightDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightDetailsRepository extends JpaRepository<FlightDetails, String> {

    List<FlightDetails> findByOriginAndDestination(String origin, String destination);

}
