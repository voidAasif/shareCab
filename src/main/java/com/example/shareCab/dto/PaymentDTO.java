package com.example.shareCab.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private Long id;
    private Double amount;
    private String method;
    private String paymentMethod;
    private LocalDateTime paymentTime;
    private String paymentStatus;
    private Long bookingId;
}
