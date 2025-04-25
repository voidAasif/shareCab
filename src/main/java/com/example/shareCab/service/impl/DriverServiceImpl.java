package com.example.shareCab.service.impl;

import com.example.shareCab.dto.DriverSignupDTO;
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
    public DriverSignupDTO getDriverById(Long id) {
        Driver driver = driverRepository.findById(id).orElseThrow();
        return DriverSignupDTO.builder()
                .firstName(driver.getFirstName())
                .lastName(driver.getLastName())
                .email(driver.getEmail())
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

