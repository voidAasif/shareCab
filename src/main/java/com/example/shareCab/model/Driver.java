package com.example.shareCab.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aadhar;
    private String driverId;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
    private String password;
    private String address;
    private String vehicleNo;
    private String profilePhoto;
    private String drivingLicense;
    private String role;
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


    // Relationships
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private java.util.List<Ride> rides;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private java.util.List<Review> reviews;
}

