package com.micriservice.repository;

import com.micriservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookingsNumberIn(List<String> bookingNumber);
}
