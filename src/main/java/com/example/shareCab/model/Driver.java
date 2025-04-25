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

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String aadhar;

    @Column(nullable = true)
    private String driverId;

    @Column(nullable = true)
    private String phoneNo;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String vehicleNo;

    @Column(nullable = true)
    private String profilePhoto;

    @Column(nullable = true)
    private String drivingLicense;

    @Column(nullable = true)
    private String role;

    @Column(nullable = true)
    private String availableStatus;

    @Column(nullable = true)
    private int rating;

    @Column(nullable = true)
    private int totalTrips;

    @Column(nullable = true)
    private String vehicleType;

    @Column(nullable = true)
    private String vehicleModel;

    @Column(nullable = true)
    private String vehicleColor;

    @Column(nullable = true)
    private int availableSeats;

    @Column(nullable = true)
    private LocalDateTime lastRideDate;

    @Column(nullable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime updatedAt;


    // Relationships
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private java.util.List<Ride> rides;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private java.util.List<Review> reviews;
}

