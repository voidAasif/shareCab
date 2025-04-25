package com.example.shareCab.controller;

import com.example.shareCab.dto.UserSignupDTO;
import com.example.shareCab.service.UserService;
import com.example.shareCab.web.response.BaseResponse;
import com.example.shareCab.web.response.SignupResponse;
import com.example.shareCab.model.User;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<SignupResponse>> register(@Valid @RequestBody UserSignupDTO userSignupDTO) {
        User user = userService.registerUser(userSignupDTO);

        //built response with user data;
        SignupResponse response = SignupResponse.builder()
                .message("User registered successfully")
                .status("success")
                .id(user.getId())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt().toString())
                .build();

        //use BaseResponse and set UserSignupResponse into data field;
        BaseResponse<SignupResponse> baseResponse = BaseResponse.<SignupResponse>builder()
                .status("success")
                .message("Registration successful")
                .data(response)
                .build();

        return new ResponseEntity<>(baseResponse, HttpStatus.CREATED); // status code: 201;
    }

    @GetMapping("/{id}")
    public UserSignupDTO getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/")
    public List<UserSignupDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

