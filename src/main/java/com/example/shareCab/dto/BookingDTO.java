package com.example.shareCab.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDTO {
    private Long id;
    private Long userId;
    private Long rideId;
    private LocalDateTime bookingDate;
    private int seatCount;
    private String bookingStatus;
}
