package com.example.shareCab.service.impl;

import com.example.shareCab.dto.DriverDTO;
import com.example.shareCab.model.Driver;
import com.example.shareCab.repository.DriverRepository;
import com.example.shareCab.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public DriverDTO registerDriver(DriverDTO dto) {
        Driver driver = Driver.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNo(dto.getPhoneNo())
                .address(dto.getAddress())
                .vehicleNo(dto.getVehicleNo())
                .profilePhoto(dto.getProfilePhoto())
                .role(dto.getRole())
                .build();

        driver = driverRepository.save(driver);
        dto.setId(driver.getId());
        return dto;
    }

    @Override
    public DriverDTO getDriverById(Long id) {
        Driver driver = driverRepository.findById(id).orElseThrow();
        return DriverDTO.builder()
                .id(driver.getId())
                .firstName(driver.getFirstName())
                .lastName(driver.getLastName())
                .email(driver.getEmail())
                .phoneNo(driver.getPhoneNo())
                .address(driver.getAddress())
                .vehicleNo(driver.getVehicleNo())
                .profilePhoto(driver.getProfilePhoto())
                .role(driver.getRole())
                .build();
    }

    @Override
    public List<DriverDTO> getAllDrivers() {
        return driverRepository.findAll().stream().map(d -> DriverDTO.builder()
                .id(d.getId())
                .firstName(d.getFirstName())
                .lastName(d.getLastName())
                .email(d.getEmail())
                .phoneNo(d.getPhoneNo())
                .address(d.getAddress())
                .vehicleNo(d.getVehicleNo())
                .profilePhoto(d.getProfilePhoto())
                .role(d.getRole())
                .build()
        ).collect(Collectors.toList());
    }

    @Override
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}

