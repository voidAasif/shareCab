package com.example.shareCab.service.impl;

import com.example.shareCab.dto.BookingDTO;
import com.example.shareCab.model.Booking;
import com.example.shareCab.model.Ride;
import com.example.shareCab.model.User;
import com.example.shareCab.repository.BookingRepository;
import com.example.shareCab.repository.RideRepository;
import com.example.shareCab.repository.UserRepository;
import com.example.shareCab.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RideRepository rideRepository;

    @Override
    public BookingDTO bookRide(BookingDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(); //find user by bookingDto user id;
        Ride ride = rideRepository.findById(dto.getRideId()).orElseThrow(); //find ride by bookingDto ride id;

        Booking booking = Booking.builder() //set bookingDto into booking entity;
                .user(user)
                .ride(ride)
                .bookingDate(dto.getBookingDate())
                .bookingStatus(dto.getBookingStatus())
                .noOfSeats(dto.getSeatCount())
                .build();

        booking = bookingRepository.save(booking); //save booking entity;
        dto.setId(booking.getId());
        return dto; //return booking dto with updated booking id;
    }

    @Override
    public BookingDTO getBookingById(Long id) { 
        Booking b = bookingRepository.findById(id).orElseThrow(); //find booking by id;
        return BookingDTO.builder() //convert booking into bookingDto;
                .id(b.getId())
                .userId(b.getUser().getId())
                .rideId(b.getRide().getId())
                .bookingDate(b.getBookingDate())
                .seatCount(b.getNoOfSeats())
                .bookingStatus(b.getBookingStatus())
                .build();
    }

    @Override
    public List<BookingDTO> getAllBookings() { //return bookingDto list of all bookings;
        return bookingRepository.findAll().stream().map(b -> BookingDTO.builder() //findAll -> fetch all booking | stream.map -> get entries oneByOne | and set booking into bookingDto;
                .id(b.getId())
                .userId(b.getUser().getId())
                .rideId(b.getRide().getId())
                .bookingDate(b.getBookingDate())
                .seatCount(b.getNoOfSeats())
                .bookingStatus(b.getBookingStatus())
                .build()
        ).collect(Collectors.toList()); //set all converted bookingDto into List;
    }

    @Override
    public void cancelBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}

