package com.example.shareCab.service;

import com.example.shareCab.dto.PaymentDTO;
import java.util.List;

public interface PaymentService {
    PaymentDTO makePayment(PaymentDTO dto);
    PaymentDTO getPaymentById(Long id);
    List<PaymentDTO> getAllPayments();
}

