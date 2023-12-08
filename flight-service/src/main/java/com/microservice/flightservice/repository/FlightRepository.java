package com.microservice.flightservice.repository;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.Request;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.microservice.flightservice.dto.FlightRequest;
import com.microservice.flightservice.dto.FlightResponse;
import com.microservice.flightservice.model.Flight;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@NoArgsConstructor
@Slf4j
public class FlightRepository {

    public List<Flight> getFlights(FlightRequest flightRequest) throws ResponseException {
        if(flightRequest == null) throw new IllegalArgumentException("FlightRequest cannot be null");

        //https://developers.amadeus.com/blog/multi-city-flight-search-amadeus-api
        Amadeus amadeus = Amadeus
                .builder("McVsFaTivp3VYSYLNdClaVl5CsJVLAqU", "pv1iHsFZORu3PbXP")
                .build();

        // Flight Offers Search v2 GET
        FlightOfferSearch[] flightOffersSearches = amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", flightRequest.getOriginLocationCode())
                        .and("destinationLocationCode", flightRequest.getDestinationLocationCode())
                        .and("departureDate", flightRequest.getDate())
                        .and("adults", 1)
        );

        List<Flight> flights = Arrays.stream(flightOffersSearches).map(flightOfferSearch -> {
            Flight flight = mapToFlight(flightOfferSearch);
            flight.setOriginLocationCode(flightRequest.originLocationCode);
            flight.setDestinationLocationCode(flightRequest.destinationLocationCode);
            flight.setArrival(flightRequest.getDate());
            flight.setDeparture(flightRequest.getDate());
            return flight;
        }).toList();

        if (flights.isEmpty()) flights = mockFlights(flightRequest);

        //TODO: create a list of flights
        return flights;
    }

    private Flight mapToFlight(FlightOfferSearch flightOfferSearch) {

        return Flight.builder()
                .id(Long.parseLong(flightOfferSearch.getId()))
                .price(new BigDecimal(flightOfferSearch.getPrice().getTotal()))
                .build();
    }

    private List<Flight> mockFlights(FlightRequest flightRequest){
        return List.of(Flight.builder()
                        .id(1L)
                        .originLocationCode(flightRequest.getOriginLocationCode())
                        .destinationLocationCode(flightRequest.getDestinationLocationCode())
                        .departure(flightRequest.getDate())
                        .arrival(flightRequest.getDate())
                        .price(new BigDecimal(112))
                        .build()
        );
    }

}
