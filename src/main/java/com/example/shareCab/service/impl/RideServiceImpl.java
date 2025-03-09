package com.example.shareCab.service.impl;

import com.example.shareCab.dto.RideDTO;
import com.example.shareCab.model.Driver;
import com.example.shareCab.model.Ride;
import com.example.shareCab.repository.DriverRepository;
import com.example.shareCab.repository.RideRepository;
import com.example.shareCab.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RideServiceImpl implements RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public RideDTO createRide(RideDTO dto) {
        Driver driver = driverRepository.findById(dto.getDriverId()).orElseThrow();

        Ride ride = Ride.builder()
                .source(dto.getSource())
                .destination(dto.getDestination())
                .departureTime(dto.getDepartureTime())
                .availableSeats(dto.getAvailableSeats())
                .farePerSeat(dto.getFarePerSeat())
                .vehicleType(dto.getVehicleType())
                .rideStatus(dto.getRideStatus())
                .driver(driver)
                .build();

        ride = rideRepository.save(ride);
        dto.setId(ride.getId());
        return dto;
    }

    @Override
    public RideDTO getRideById(Long id) {
        Ride ride = rideRepository.findById(id).orElseThrow();
        return RideDTO.builder()
                .id(ride.getId())
                .source(ride.getSource())
                .destination(ride.getDestination())
                .departureTime(ride.getDepartureTime())
                .availableSeats(ride.getAvailableSeats())
                .farePerSeat(ride.getFarePerSeat())
                .vehicleType(ride.getVehicleType())
                .rideStatus(ride.getRideStatus())
                .driverId(ride.getDriver().getId())
                .build();
    }

    @Override
    public List<RideDTO> getAllRides() {
        return rideRepository.findAll().stream().map(r ->
            RideDTO.builder()
                .id(r.getId())
                .source(r.getSource())
                .destination(r.getDestination())
                .departureTime(r.getDepartureTime())
                .availableSeats(r.getAvailableSeats())
                .farePerSeat(r.getFarePerSeat())
                .vehicleType(r.getVehicleType())
                .rideStatus(r.getRideStatus())
                .driverId(r.getDriver().getId())
                .build()
        ).collect(Collectors.toList());
    }

    @Override
    public void deleteRide(Long id) {
        rideRepository.deleteById(id);
    }
}

