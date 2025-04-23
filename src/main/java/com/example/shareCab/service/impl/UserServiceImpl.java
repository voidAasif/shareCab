package com.example.shareCab.service.impl;

import com.example.shareCab.dto.UserSignupDTO;
import com.example.shareCab.model.User;
import com.example.shareCab.repository.UserRepository;
import com.example.shareCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserSignupDTO registerUser(UserSignupDTO dto) {
        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
        user = userRepository.save(user);
        return dto;
    }

    @Override
    public UserSignupDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return UserSignupDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
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

