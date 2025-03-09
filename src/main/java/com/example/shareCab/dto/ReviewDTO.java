package com.example.shareCab.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {
    private Long id;
    private Integer rating;
    private String comment;
    private LocalDateTime reviewTime;
    private Long userId;
    private Long driverId;
}
