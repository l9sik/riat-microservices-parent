package com.micriservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingLineItemDto {

    private String destinationIataCode;
    private String arrivalIataCode;
    private String departureTime;
    private String arrivalTime;
}
