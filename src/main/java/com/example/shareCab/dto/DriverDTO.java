package com.example.shareCab.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
    private String address;
    private String vehicleNo;
    private String profilePhoto;
    private String role;
}

