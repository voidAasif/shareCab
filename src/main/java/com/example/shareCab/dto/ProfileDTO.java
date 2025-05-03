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
public class ProfileDTO {

    private String firstName;
    private String lastName;
    private String phoneNo;
    private String address;
    private String profilePhoto;
    private String aadhar;
    private int rating;
    private int totalRides;
    private LocalDateTime lastRideDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
