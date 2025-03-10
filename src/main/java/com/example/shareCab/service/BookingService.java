package com.example.shareCab.service;

import com.example.shareCab.dto.BookingDTO;
import java.util.List;

public interface BookingService { //autowired by booking controller; and implementation provide in impl/bookingServiceImpl;
    BookingDTO bookRide(BookingDTO bookingDTO);
    BookingDTO getBookingById(Long id);
    List<BookingDTO> getAllBookings();
    void cancelBooking(Long id);
}

