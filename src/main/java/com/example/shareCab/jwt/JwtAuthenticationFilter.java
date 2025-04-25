package com.example.shareCab.jwt;

import com.example.shareCab.userdetails.CombinedUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CombinedUserDetailsService combinedUserDetailsService; // Used to load user by username
    private final JwtUtils jwtUtils; // Used for token operations

    // Constructor injection for JwtUtil and CustomUserDetailsService
    public JwtAuthenticationFilter(CombinedUserDetailsService combinedUserDetailsService, JwtUtils jwtUtils) {
        this.combinedUserDetailsService = combinedUserDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @SuppressWarnings("all")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization"); // Get Authorization header
        System.out.println("Authorization Header: " + authHeader); //log;
        String username = null;
        String jwtToken = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) { // Check token
            jwtToken = authHeader.substring(7); // Extract token

            try {
                username = jwtUtils.extractUsername(jwtToken); // Get username from token
                System.out.println("token: " + jwtToken); //logs;
                System.out.println("username from token: " + username); //logs;
            } catch (Exception e) {
                logger.warn("Invalid JWT token: " + e.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { // Check username and if user is not authenticated
            UserDetails userDetails = combinedUserDetailsService.loadUserByUsername(username); // Load user by username

            if (jwtUtils.validateToken(jwtToken, userDetails)) { // Validate token by token and userDetails
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()); // Authenticate user by userDetails, credentials, and role

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Build request details
                SecurityContextHolder.getContext().setAuthentication(authToken); // Set authenticated token
            }
        }

        filterChain.doFilter(request, response); // Continue the security chain
    }
}
