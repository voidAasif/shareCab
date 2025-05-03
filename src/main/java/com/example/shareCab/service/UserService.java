package com.example.shareCab.service;

import com.example.shareCab.dto.ProfileDTO;
import com.example.shareCab.dto.UserSignupDTO;
import com.example.shareCab.model.User;

import java.util.List;

public interface UserService {
    User registerUser(UserSignupDTO userSignupDTO);
    ProfileDTO getUserById(Long id);
    ProfileDTO updateUserProfile(Long id, ProfileDTO profileDTO);
    List<UserSignupDTO> getAllUsers();
    void deleteUser(Long id);
}

