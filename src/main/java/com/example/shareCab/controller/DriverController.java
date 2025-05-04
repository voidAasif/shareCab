package com.example.shareCab.controller;

import com.example.shareCab.dto.DriverProfileDTO;
import com.example.shareCab.dto.DriverSignupDTO;
import com.example.shareCab.model.Driver;
import com.example.shareCab.service.DriverService;
import com.example.shareCab.web.response.BaseResponse;
import com.example.shareCab.web.response.SignupResponse;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<SignupResponse>> registerDriver(@Valid @RequestBody DriverSignupDTO driverSignupDTO) {


        Driver driver = driverService.registerDriver(driverSignupDTO);

        //built response with user data;
        SignupResponse response = SignupResponse.builder()
                .message("Driver registered successfully")
                .status("success")
                .id(driver.getId())
                .email(driver.getEmail())
                .createdAt(driver.getCreatedAt().toString())
                .build();

        //use BaseResponse and set UserSignupResponse into data field;
        BaseResponse<SignupResponse> baseResponse = BaseResponse.<SignupResponse>builder()
                .status("success")
                .message("Registration successful")
                .data(response)
                .build();

        return new ResponseEntity<>(baseResponse, HttpStatus.CREATED); // status code: 201;
    }

    @GetMapping("/{id}") //get driver profile;
    public ResponseEntity<BaseResponse<DriverProfileDTO>> getDriverProfile(@PathVariable Long id) {
        BaseResponse<DriverProfileDTO> baseResponse = BaseResponse.<DriverProfileDTO>builder()
                .status("success")
                .message("Profile Fetch Successful")
                .data(driverService.getDriverById(id))
                .build();

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}") //update driver profile;
    public ResponseEntity<BaseResponse<DriverProfileDTO>> updateUser(@PathVariable Long id, @RequestBody DriverProfileDTO driverProfileDTO) {

        BaseResponse<DriverProfileDTO> baseResponse = BaseResponse.<DriverProfileDTO>builder()
                .status("success")
                .message("Profile Update Successful")
                .data(driverService.updateDriverProfile(id, driverProfileDTO))
                .build();

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/")
    public List<DriverSignupDTO> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
    }
}

