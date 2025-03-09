package com.example.shareCab.service.impl;

import com.example.shareCab.dto.UserDTO;
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
    public UserDTO registerUser(UserDTO dto) {
        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNo(dto.getPhoneNo())
                .address(dto.getAddress())
                .aadhar(dto.getAadhar())
                .role(dto.getRole())
                .profilePhoto(dto.getProfilePhoto())
                .build();
        user = userRepository.save(user);
        dto.setId(user.getId());
        return dto;
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNo(user.getPhoneNo())
                .address(user.getAddress())
                .aadhar(user.getAadhar())
                .role(user.getRole())
                .profilePhoto(user.getProfilePhoto())
                .build();
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(u ->
            UserDTO.builder()
                .id(u.getId())
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .email(u.getEmail())
                .phoneNo(u.getPhoneNo())
                .address(u.getAddress())
                .aadhar(u.getAadhar())
                .role(u.getRole())
                .profilePhoto(u.getProfilePhoto())
                .build()
        ).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

