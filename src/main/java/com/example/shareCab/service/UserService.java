package com.example.shareCab.service;

import com.example.shareCab.dto.UserSignupDTO;

import java.util.List;

public interface UserService {
    UserSignupDTO registerUser(UserSignupDTO userSignupDTO);
    UserSignupDTO getUserById(Long id);
    List<UserSignupDTO> getAllUsers();
    void deleteUser(Long id);
}

