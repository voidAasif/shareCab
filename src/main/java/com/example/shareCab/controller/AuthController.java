package com.example.shareCab.controller;

import com.example.shareCab.jwt.JwtUtils;
import com.example.shareCab.userdetails.CombinedUserDetailsService;
import com.example.shareCab.web.request.JwtRequest;
import com.example.shareCab.web.response.BaseResponse;
import com.example.shareCab.web.response.JwtResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CombinedUserDetailsService combinedUserDetailsService; // it has both user and driver;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody JwtRequest jwtRequest) {

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
        );

        UserDetails userDetails = combinedUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

        //generate JWT token;
        String jwtToken = jwtUtils.generateToken(userDetails);

        //set JWT token into response;
        JwtResponse jwtResponse = new JwtResponse(jwtToken);

        //set response into baseResponse;
        BaseResponse<JwtResponse> response = BaseResponse.<JwtResponse>builder()
            .status("success")
            .message("Login successful")
            .data(jwtResponse)
            .build();

        return ResponseEntity.ok(response);
    }
}
