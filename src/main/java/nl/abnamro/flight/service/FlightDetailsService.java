package nl.abnamro.flight.service;

import lombok.extern.slf4j.Slf4j;
import nl.abnamro.flight.model.FlightDetails;
import nl.abnamro.flight.repository.FlightDetailsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FlightDetailsService {

    private final FlightDetailsRepository flightDetailsRepository;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

    public FlightDetailsService(FlightDetailsRepository flightDetailsRepository) {this.flightDetailsRepository = flightDetailsRepository;}

    public List<FlightDetails> shortListFlightDetails(String origin, String destination, Map<String, String> filteringParams) {
        LocalTime startDepartureTime = LocalTime.parse(filteringParams.getOrDefault("startDepartureTime", "00:00"), dtf);
        LocalTime endDepartureTime = LocalTime.parse(filteringParams.getOrDefault("endDepartureTime", "23:59"), dtf);

        LocalTime startArrivalTIme = LocalTime.parse(filteringParams.getOrDefault("startArrivalTime", "00:00"), dtf);
        LocalTime endArrivalTime = LocalTime.parse(filteringParams.getOrDefault("endArrivalTime", "23:59"), dtf);

        return flightDetailsRepository.findByOriginAndDestination(origin, destination).stream()
        .filter(l -> l.getDepartureTime().isAfter(startDepartureTime) && l.getDepartureTime().isBefore(endDepartureTime))
        .filter(l -> l.getArrivalTime().isAfter(startArrivalTIme) && l.getArrivalTime().isBefore(endArrivalTime))
        .filter(l -> !Objects.nonNull(filteringParams.get("price")) || l.getPrice() <= Integer.parseInt(filteringParams.get("price")))
        .filter(l -> !Objects.nonNull(filteringParams.get("travelTime")) || getTravelTime(l.getDepartureTime(), l.getArrivalTime()) <= Long.parseLong(filteringParams.get("travelTime")))
        .collect(Collectors.toList());
    }

    private long getTravelTime(LocalTime departureTime, LocalTime arrivalTime) {
        long journeyTime = ChronoUnit.HOURS.between(departureTime, arrivalTime);
        return (journeyTime < 0) ? (24 + journeyTime) : journeyTime;

    }

}
