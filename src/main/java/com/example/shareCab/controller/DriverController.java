package com.example.shareCab.controller;

import com.example.shareCab.dto.DriverDTO;
import com.example.shareCab.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/register")
    public DriverDTO registerDriver(@RequestBody DriverDTO dto) {
        return driverService.registerDriver(dto);
    }

    @GetMapping("/{id}")
    public DriverDTO getDriver(@PathVariable Long id) {
        return driverService.getDriverById(id);
    }

    @GetMapping("/")
    public List<DriverDTO> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
    }
}

