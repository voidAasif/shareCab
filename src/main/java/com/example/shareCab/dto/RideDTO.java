package com.example.shareCab.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RideDTO {
    private Long id;
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private int availableSeats;
    private Double farePerSeat;
    private String vehicleType;
    private String rideStatus;
    private Long driverId;
}

