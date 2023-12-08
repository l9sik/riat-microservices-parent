package com.micriservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "booking_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinationIataCode;
    private String arrivalIataCode;
    private String departureTime;
    private String arrivalTime;
}
