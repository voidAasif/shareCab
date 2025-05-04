package com.example.shareCab.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverProfileDTO {

    private String firstName;
    private String lastName;
    private String aadhar;
    private String phoneNo;
    private String address;
    private String vehicleNo;
    private String profilePhoto;
    private String drivingLicense;
    private String availableStatus;
    private int rating;
    private int totalTrips;
    private String vehicleType;
    private String vehicleModel;
    private String vehicleColor;
    private int availableSeats;
    private LocalDateTime lastRideDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
