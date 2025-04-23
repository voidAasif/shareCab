package com.example.shareCab.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignupDTO {
    // private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}

