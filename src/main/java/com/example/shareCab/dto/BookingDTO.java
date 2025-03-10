package com.example.shareCab.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDTO {
    private Long id;
    private Long userId;
    private Long rideId;
    private LocalDateTime bookingDate;
    private int seatCount;
    private String bookingStatus;
}

//what?
//DTO stands for Data Transfer Object;

//why?
//Its a template code only used to interact with Frontend or clint;
//DTO is used to unexposed our entities to clint;

//how?
//clint -> DTO -> entities -> DB;

//use-case?
//entity has -> {name, password, roll, address, class, fatherName, password};
//but we only need {name, roll, class} for signup;
//case1: directly expose entity {which have password etc} to the clint; but it takes security issue;
//case2: create signupDTO -> {name, roll, class} // expose this DTO without {other field like password};