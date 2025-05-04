package com.example.shareCab.service.impl;

import com.example.shareCab.dto.UserProfileDTO;
import com.example.shareCab.dto.UserSignupDTO;
import com.example.shareCab.exceptions.ResourceNotFoundException;
import com.example.shareCab.model.User;
import com.example.shareCab.repository.UserRepository;
import com.example.shareCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserSignupDTO userSignupDTO) {
        User user = User.builder()
                .firstName(userSignupDTO.getFirstName())
                .lastName(userSignupDTO.getLastName())
                .email(userSignupDTO.getEmail())
                .password(passwordEncoder.encode(userSignupDTO.getPassword()))
                .role("USER")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        user = userRepository.save(user);
        return user;
    }

    @Override
    public UserProfileDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("userId: " + id + " | Not found in DB"));
        return UserProfileDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNo(user.getPhoneNo())
                .address(user.getAddress())
                .profilePhoto(user.getProfilePhoto())
                .aadhar(user.getAadhar())
                .rating(user.getRating())
                .totalRides(user.getTotalRides())
                .lastRideDate(user.getLastRideDate())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    @Override
    public UserProfileDTO updateUserProfile(Long id, UserProfileDTO userProfileDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("userId: " + id + " | Not found in DB"));

        // Update fields only if they are non-null
        if (userProfileDTO.getFirstName() != null) user.setFirstName(userProfileDTO.getFirstName());
        if (userProfileDTO.getLastName() != null) user.setLastName(userProfileDTO.getLastName());
        if (userProfileDTO.getPhoneNo() != null) user.setPhoneNo(userProfileDTO.getPhoneNo());
        if (userProfileDTO.getAddress() != null) user.setAddress(userProfileDTO.getAddress());
        if (userProfileDTO.getProfilePhoto() != null) user.setProfilePhoto(userProfileDTO.getProfilePhoto());
        if (userProfileDTO.getAadhar() != null) user.setAadhar(userProfileDTO.getAadhar());

        user.setUpdatedAt(LocalDateTime.now()); // update timestamp


        // Save updated user
        userRepository.save(user);

        // Return updated UserProfileDTO
        return UserProfileDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNo(user.getPhoneNo())
                .address(user.getAddress())
                .profilePhoto(user.getProfilePhoto())
                .aadhar(user.getAadhar())
                .rating(user.getRating())
                .totalRides(user.getTotalRides())
                .lastRideDate(user.getLastRideDate())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    @Override
    public List<UserSignupDTO> getAllUsers() {
        return userRepository.findAll().stream().map(u ->
            UserSignupDTO.builder()
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .email(u.getEmail())
                .build()
        ).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

