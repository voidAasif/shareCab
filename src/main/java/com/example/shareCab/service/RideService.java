package com.example.shareCab.service;

import com.example.shareCab.dto.RideDTO;

import java.util.List;

public interface RideService {
    RideDTO createRide(RideDTO rideDTO);
    RideDTO getRideById(Long id);
    List<RideDTO> getAllRides();
    void deleteRide(Long id);
}
