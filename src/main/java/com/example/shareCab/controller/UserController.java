package com.example.shareCab.controller;

import com.example.shareCab.dto.UserSignupDTO;
import com.example.shareCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserSignupDTO register(@RequestBody UserSignupDTO userSignupDTO) {
        return userService.registerUser(userSignupDTO);
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

