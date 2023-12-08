package com.microservice.flightservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightResponse {

    private Long id;

    public String originLocationCode;
    public String destinationLocationCode;


    private String departure;
    private String arrival;
    private String date;
    private BigDecimal price;
}
