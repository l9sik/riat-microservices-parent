package com.micriservice.service;

import com.micriservice.dto.BookingLineItemDto;
import com.micriservice.dto.BookingRequest;
import com.micriservice.model.Booking;
import com.micriservice.model.BookingItem;
import com.micriservice.repository.BookingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;

    public String placeBooking(BookingRequest bookingRequest) {

        Booking booking = new Booking();
        booking.setBookingsNumber(UUID.randomUUID().toString());

        List<BookingItem> bookingItemList = bookingRequest.getBookingLineItemDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        booking.setBookingItemList(bookingItemList);

        bookingRepository.save(booking);
        log.info("Added booking: {}", booking);
        return booking.getBookingsNumber();
    }

    private BookingItem mapToDto(BookingLineItemDto bookingLineItemDto) {

        BookingItem bookingItem = new BookingItem();
        bookingItem.setDestinationIataCode(bookingLineItemDto.getDestinationIataCode());
        bookingItem.setArrivalIataCode(bookingLineItemDto.getArrivalIataCode());
        bookingItem.setDepartureTime(bookingLineItemDto.getDepartureTime());
        bookingItem.setArrivalTime(bookingLineItemDto.getArrivalTime());

        return bookingItem;
    }

    public List<Booking> getBookings(List<String> bookingNumber) {
        return bookingRepository.findByBookingsNumberIn(bookingNumber);
    }
}
