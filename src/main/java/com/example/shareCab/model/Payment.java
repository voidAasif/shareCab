package com.example.shareCab.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentMethod;
    private Double amount;
    private LocalDateTime paymentTime;
    private String paymentStatus;
    private String transactionId;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}

