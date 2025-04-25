package com.example.shareCab.service;

import com.example.shareCab.dto.DriverSignupDTO;
import com.example.shareCab.model.Driver;

import java.util.List;

public interface DriverService {
    Driver registerDriver(DriverSignupDTO driverSignupDTO);
    DriverSignupDTO getDriverById(Long id);
    List<DriverSignupDTO> getAllDrivers();
    void deleteDriver(Long id);
}

