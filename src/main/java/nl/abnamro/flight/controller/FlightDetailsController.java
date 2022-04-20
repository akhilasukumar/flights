package nl.abnamro.flight.controller;

import lombok.extern.slf4j.Slf4j;
import nl.abnamro.flight.service.FlightDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
@Slf4j
@Validated
public class FlightDetailsController {

    private final FlightDetailsService flightDetailsService;

    public FlightDetailsController(final FlightDetailsService flightDetailsService) {
        this.flightDetailsService = flightDetailsService;
    }

    @GetMapping(value = "/{origin}/{destination}")
    public ResponseEntity shortListFlights(@RequestParam(required = false) Map<String, String> filteringParams,
                                           @PathVariable @NotNull @Pattern(regexp = "^[A-Za-z]{3}$", message = "Invalid origin") String origin,
                                           @PathVariable @NotNull @Pattern(regexp = "^[A-Za-z]{3}$", message = "Invalid " +
                                           "destination") String destination) {
        filteringParams.entrySet().forEach(entry -> log.info("key {} and value {}", entry.getKey(), entry.getValue()));

        return Optional.ofNullable(flightDetailsService.shortListFlightDetails(origin, destination, filteringParams))
        .filter(list -> !list.isEmpty())
        .map(details -> (ResponseEntity) ResponseEntity.ok(details))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
