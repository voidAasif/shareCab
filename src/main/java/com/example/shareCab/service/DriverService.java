package com.example.shareCab.service;

import com.example.shareCab.dto.DriverDTO;

import java.util.List;

public interface DriverService {
    DriverDTO registerDriver(DriverDTO driverDTO);
    DriverDTO getDriverById(Long id);
    List<DriverDTO> getAllDrivers();
    void deleteDriver(Long id);
}

