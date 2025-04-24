package com.example.shareCab.service;

import com.example.shareCab.dto.UserSignupDTO;
import com.example.shareCab.model.User;

import java.util.List;

public interface UserService {
    User registerUser(UserSignupDTO userSignupDTO);
    UserSignupDTO getUserById(Long id);
    List<UserSignupDTO> getAllUsers();
    void deleteUser(Long id);
}

