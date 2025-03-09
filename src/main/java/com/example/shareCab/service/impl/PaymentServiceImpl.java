package com.example.shareCab.service.impl;

import com.example.shareCab.dto.PaymentDTO;
import com.example.shareCab.model.Booking;
import com.example.shareCab.model.Payment;
import com.example.shareCab.repository.BookingRepository;
import com.example.shareCab.repository.PaymentRepository;
import com.example.shareCab.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public PaymentDTO makePayment(PaymentDTO dto) {
        Booking booking = bookingRepository.findById(dto.getBookingId()).orElseThrow();

        Payment payment = Payment.builder()
                .booking(booking)
                .amount(dto.getAmount())
                .paymentMethod(dto.getPaymentMethod())
                .paymentTime(dto.getPaymentTime())
                .paymentStatus(dto.getPaymentStatus())
                .build();

        payment = paymentRepository.save(payment);
        dto.setId(payment.getId());
        return dto;
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Payment p = paymentRepository.findById(id).orElseThrow();
        return PaymentDTO.builder()
                .id(p.getId())
                .bookingId(p.getBooking().getId())
                .amount(p.getAmount())
                .paymentMethod(p.getPaymentMethod())
                .paymentTime(p.getPaymentTime())
                .paymentStatus(p.getPaymentStatus())
                .build();
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream().map(p -> PaymentDTO.builder()
                .id(p.getId())
                .bookingId(p.getBooking().getId())
                .amount(p.getAmount())
                .paymentMethod(p.getPaymentMethod())
                .paymentTime(p.getPaymentTime())
                .paymentStatus(p.getPaymentStatus())
                .build()
        ).collect(Collectors.toList());
    }
}

