package com.example.shareCab.service.impl;

import com.example.shareCab.dto.ReviewDTO;
import com.example.shareCab.model.Driver;
import com.example.shareCab.model.Review;
import com.example.shareCab.model.User;
import com.example.shareCab.repository.DriverRepository;
import com.example.shareCab.repository.ReviewRepository;
import com.example.shareCab.repository.UserRepository;
import com.example.shareCab.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public ReviewDTO giveReview(ReviewDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        Driver driver = driverRepository.findById(dto.getDriverId()).orElseThrow();

        Review review = Review.builder()
                .user(user)
                .driver(driver)
                .rating(dto.getRating())
                .comment(dto.getComment())
                .reviewTime(dto.getReviewTime())
                .build();

        review = reviewRepository.save(review);
        dto.setId(review.getId());
        return dto;
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream().map(r -> ReviewDTO.builder()
                .id(r.getId())
                .userId(r.getUser().getId())
                .driverId(r.getDriver().getId())
                .rating(r.getRating())
                .comment(r.getComment())
                .reviewTime(r.getReviewTime())
                .build()
        ).collect(Collectors.toList());
    }
}

