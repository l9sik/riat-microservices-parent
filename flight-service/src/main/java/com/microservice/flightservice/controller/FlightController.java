package com.microservice.flightservice.controller;

import com.amadeus.exceptions.ResponseException;
import com.microservice.flightservice.dto.FlightRequest;
import com.microservice.flightservice.dto.FlightResponse;
import com.microservice.flightservice.model.Flight;
import com.microservice.flightservice.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
@RequiredArgsConstructor
public class FlightController {
    @Autowired
    FlightService flightService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FlightResponse> getFlight(@RequestParam("origin") String origin,
                                          @RequestParam("destination") String destination,
                                          @RequestParam("date") String date) throws ResponseException {
        FlightRequest request = FlightRequest.builder()
                .originLocationCode(origin)
                .destinationLocationCode(destination)
                .date(date)
                .build();
        return flightService.getFlights(request);
    }


}
