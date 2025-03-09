package com.example.shareCab.service;

import com.example.shareCab.dto.ReviewDTO;
import java.util.List;

public interface ReviewService {
    ReviewDTO giveReview(ReviewDTO dto);
    List<ReviewDTO> getAllReviews();
}

