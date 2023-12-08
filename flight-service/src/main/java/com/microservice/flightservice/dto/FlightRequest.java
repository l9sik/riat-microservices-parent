package com.microservice.flightservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightRequest {

    public String originLocationCode;
    public String destinationLocationCode;

    private String date;
}
