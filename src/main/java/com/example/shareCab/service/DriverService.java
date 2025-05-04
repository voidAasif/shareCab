package com.example.shareCab.service;

import com.example.shareCab.dto.DriverProfileDTO;
import com.example.shareCab.dto.DriverSignupDTO;
import com.example.shareCab.model.Driver;

import java.util.List;

public interface DriverService {
    Driver registerDriver(DriverSignupDTO driverSignupDTO);
    DriverProfileDTO getDriverById(Long id);
    DriverProfileDTO updateDriverProfile(Long id, DriverProfileDTO driverProfileDTO);
    List<DriverSignupDTO> getAllDrivers();
    void deleteDriver(Long id);
}

