package com.example.shareCab.controller;

import com.example.shareCab.dto.RideDTO;
import com.example.shareCab.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @PostMapping("/create")
    public RideDTO createRide(@RequestBody RideDTO rideDTO) {
        return rideService.createRide(rideDTO);
    }

    @GetMapping("/{id}")
    public RideDTO getRide(@PathVariable Long id) {
        return rideService.getRideById(id);
    }

    @GetMapping("/")
    public List<RideDTO> getAllRides() {
        return rideService.getAllRides();
    }

    @DeleteMapping("/{id}")
    public void deleteRide(@PathVariable Long id) {
        rideService.deleteRide(id);
    }
}

