package com.micriservice.controller;

import com.micriservice.dto.BookingRequest;
import com.micriservice.model.Booking;
import com.micriservice.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeBooking(@RequestBody BookingRequest bookingRequest){
        String bookNumber = bookingService.placeBooking(bookingRequest);
        return "Booking added successfully: " + bookNumber;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<Booking> getBookings(@RequestParam ("bookingNumber") List<String> bookingNumber){
        return bookingService.getBookings(bookingNumber);
    }

}