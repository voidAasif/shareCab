package com.example.shareCab.service;

import com.example.shareCab.dto.UserProfileDTO;
import com.example.shareCab.dto.UserSignupDTO;
import com.example.shareCab.model.User;

import java.util.List;

public interface UserService {
    User registerUser(UserSignupDTO userSignupDTO);
    UserProfileDTO getUserById(Long id);
    UserProfileDTO updateUserProfile(Long id, UserProfileDTO profileDTO);
    List<UserSignupDTO> getAllUsers();
    void deleteUser(Long id);
}

