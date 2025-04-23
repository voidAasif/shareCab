package com.example.shareCab.controller;

import com.example.shareCab.dto.PaymentDTO;
import com.example.shareCab.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public PaymentDTO makePayment(@RequestBody PaymentDTO dto) {
        return paymentService.makePayment(dto);
    }

    @GetMapping("/{id}")
    public PaymentDTO getPayment(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/")
    public List<PaymentDTO> getAllPayments() {
        return paymentService.getAllPayments();
    }
}

