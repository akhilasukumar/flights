package nl.abnamro.flight.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "FLIGHT_DETAILS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightDetails {

    @Id
    @Column(name = "flightNumber")
    private String flightNumber;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "departureTime")
    private LocalTime departureTime;

    @Column(name = "arrivalTime")
    private LocalTime arrivalTime;

    @Column(name = "price")
    private int price;

    @Override
    public String toString() {
        return "FlightDetails{" +
        "flightNumber=" + flightNumber +
        ", origin='" + origin + '\'' +
        ", destination='" + destination + '\'' +
        ", arrivalTime='" + arrivalTime + '\'' +
        ", departureTime=" + departureTime +
        ", price='" + price +
        '}';
    }
}
