package com.microservice.flightservice.service;

import com.amadeus.exceptions.ResponseException;
import com.microservice.flightservice.dto.FlightRequest;
import com.microservice.flightservice.dto.FlightResponse;
import com.microservice.flightservice.model.Flight;
import com.microservice.flightservice.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightService {

    private final FlightRepository flightRepository;
    private final AmqpTemplate amqpTemplate;

    public List<FlightResponse> getFlights(FlightRequest flightRequest) throws ResponseException {
        List<Flight> flights;
        try {
            flights = flightRepository.getFlights(flightRequest);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Exception in getFlights: {}", e);
            throw e;
        }


        log.info("Got flights: {}", flights);

        amqpTemplate.convertAndSend("loggerQueue", "Got flights: " + flights);

        return flights.stream().map(this::mapToFlightResponse).toList();
    }

    private FlightResponse mapToFlightResponse(Flight flight) {
        return FlightResponse.builder()
                .id(flight.getId())
                .originLocationCode(flight.getOriginLocationCode())
                .destinationLocationCode(flight.getDestinationLocationCode())
                .departure(flight.getDeparture())
                .arrival(flight.getArrival())
                .price(flight.getPrice())
                .build();
    }
}
