package com.example.shareCab.controller;

import com.example.shareCab.dto.ReviewDTO;
import com.example.shareCab.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin("*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/submit")
    public ReviewDTO giveReview(@RequestBody ReviewDTO dto) {
        return reviewService.giveReview(dto);
    }

    @GetMapping("/")
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }
}

