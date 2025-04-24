package com.example.shareCab.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupResponse {
    private String message;
    private String status;
    private Long userId;
    private String email;
    private String createdAt;
}
