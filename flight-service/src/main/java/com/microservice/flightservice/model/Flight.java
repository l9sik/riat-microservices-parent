package com.microservice.flightservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Flight {
    private Long id;

    public String originLocationCode;
    public String destinationLocationCode;


    private String departure;
    private String arrival;

    private BigDecimal price;
}
