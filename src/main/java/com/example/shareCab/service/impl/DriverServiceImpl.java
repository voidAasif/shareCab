package com.example.shareCab.service.impl;

import com.example.shareCab.dto.DriverProfileDTO;
import com.example.shareCab.dto.DriverSignupDTO;
import com.example.shareCab.exceptions.ResourceNotFoundException;
import com.example.shareCab.model.Driver;
import com.example.shareCab.repository.DriverRepository;
import com.example.shareCab.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Driver registerDriver(DriverSignupDTO driverSignupDTO) {
        Driver driver = Driver.builder()
                .firstName(driverSignupDTO.getFirstName())
                .lastName(driverSignupDTO.getLastName())
                .email(driverSignupDTO.getEmail())
                .password(passwordEncoder.encode(driverSignupDTO.getPassword()))
                .role("DRIVER")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        driver = driverRepository.save(driver);
        return driver;
    }

    @Override
    public DriverProfileDTO getDriverById(Long id) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("driverId: " + id + " | Not found in DB"));
        return DriverProfileDTO.builder()
                .firstName(driver.getFirstName())
                .lastName(driver.getLastName())
                .aadhar(driver.getAadhar())
                .phoneNo(driver.getPhoneNo())
                .address(driver.getAddress())
                .vehicleNo(driver.getVehicleNo())
                .profilePhoto(driver.getProfilePhoto())
                .drivingLicense(driver.getDrivingLicense())
                .availableStatus(driver.getAvailableStatus())
                .rating(driver.getRating())
                .totalTrips(driver.getTotalTrips())
                .vehicleType(driver.getVehicleType())
                .vehicleModel(driver.getVehicleModel())
                .vehicleColor(driver.getVehicleColor())
                .availableSeats(driver.getAvailableSeats())
                .lastRideDate(driver.getLastRideDate())
                .createdAt(driver.getCreatedAt())
                .updatedAt(driver.getUpdatedAt())
                .build();
    }

    @Override
    public DriverProfileDTO updateDriverProfile(Long id, DriverProfileDTO driverProfileDTO) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("driverId: " + id + " | Not found in DB"));

        // Update fields only if they are non-null
        if (driverProfileDTO.getFirstName() != null) driver.setFirstName(driverProfileDTO.getFirstName());
        if (driverProfileDTO.getLastName() != null) driver.setLastName(driverProfileDTO.getLastName());
        if (driverProfileDTO.getAadhar() != null) driver.setAadhar(driverProfileDTO.getAadhar());
        if (driverProfileDTO.getPhoneNo() != null) driver.setPhoneNo(driverProfileDTO.getPhoneNo());
        if (driverProfileDTO.getAddress() != null) driver.setAddress(driverProfileDTO.getAddress());
        if (driverProfileDTO.getVehicleNo() != null) driver.setVehicleNo(driverProfileDTO.getVehicleNo());
        if (driverProfileDTO.getProfilePhoto() != null) driver.setProfilePhoto(driverProfileDTO.getProfilePhoto());
        if (driverProfileDTO.getDrivingLicense() != null) driver.setDrivingLicense(driverProfileDTO.getDrivingLicense());
        if (driverProfileDTO.getVehicleType() != null) driver.setVehicleType(driverProfileDTO.getVehicleType());
        if (driverProfileDTO.getVehicleModel() != null) driver.setVehicleModel(driverProfileDTO.getVehicleModel());
        if (driverProfileDTO.getVehicleColor() != null) driver.setVehicleColor(driverProfileDTO.getVehicleColor());
        if (driverProfileDTO.getAvailableSeats() != 0) driver.setAvailableSeats(driverProfileDTO.getAvailableSeats());

        driver.setUpdatedAt(LocalDateTime.now());


        //Save Updated Driver;
        driverRepository.save(driver);

        //Return updated DriverProfileDTO;
        return DriverProfileDTO.builder()
                .firstName(driver.getFirstName())
                .lastName(driver.getLastName())
                .aadhar(driver.getAadhar())
                .phoneNo(driver.getPhoneNo())
                .address(driver.getAddress())
                .vehicleNo(driver.getVehicleNo())
                .profilePhoto(driver.getProfilePhoto())
                .drivingLicense(driver.getDrivingLicense())
                .availableStatus(driver.getAvailableStatus())
                .rating(driver.getRating())
                .totalTrips(driver.getTotalTrips())
                .vehicleType(driver.getVehicleType())
                .vehicleModel(driver.getVehicleModel())
                .vehicleColor(driver.getVehicleColor())
                .availableSeats(driver.getAvailableSeats())
                .lastRideDate(driver.getLastRideDate())
                .createdAt(driver.getCreatedAt())
                .updatedAt(driver.getUpdatedAt())
                .build();
    }

    @Override
    public List<DriverSignupDTO> getAllDrivers() {
        return driverRepository.findAll().stream().map(d -> DriverSignupDTO.builder()
                .firstName(d.getFirstName())
                .lastName(d.getLastName())
                .email(d.getEmail())
                .build()
        ).collect(Collectors.toList());
    }

    @Override
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    
}

