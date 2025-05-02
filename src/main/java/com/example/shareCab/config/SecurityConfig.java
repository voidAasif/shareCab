package com.example.shareCab.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.shareCab.jwt.JwtAuthenticationEntryPoint;
import com.example.shareCab.jwt.JwtAuthenticationFilter;
import com.example.shareCab.userdetails.CombinedUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean //Bean is used to create Object, we use it by @Autowired annotation;
    public UserDetailsService userDetailsService() { // UserDetailsService used to set user credentials into Spring Security;
        return new CombinedUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){ // used to encode and decode user password;
        return new BCryptPasswordEncoder();
    }

    @Bean //Configures authentication provider to validate user credentials;
    public DaoAuthenticationProvider authenticationProvider() { //use above Beans hear;
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.userDetailsService()); // set UserDetailsService;
        provider.setPasswordEncoder(this.passwordEncoder()); // set BCryptPasswordEncoder;
        return provider;
    }

    @Bean // set DaoAuthenticationProvider in Spring Security;
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return new ProviderManager(List.of(authenticationProvider())); 
    }

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Bean // Defines security rules and configurations for handling requests;
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //view access configuration;
        http
            .cors(cors -> cors.configurationSource(request -> {
                var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                corsConfig.setAllowedOrigins(List.of("http://localhost:5173"));
                corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                corsConfig.setAllowedHeaders(List.of("*"));
                corsConfig.setAllowCredentials(true);
                return corsConfig;
            }))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth //config for authentication;
                        .requestMatchers("/api/users/register", "/api/users/login", "/api/drivers/register", "/api/drivers/login", "/api/auth/**")
                        .permitAll() //all requestMatchers permit;
                        .requestMatchers("/api/drivers/**").hasRole("DRIVER") // Only DRIVER can access /drivers/**
                        .requestMatchers("/api/users/**").hasRole("USER") // Only USER can access /users/**
                        .anyRequest().authenticated()) //remains secure;
                        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) //add JwtAuthenticationFilter before config;
                        .exceptionHandling(exception -> exception.authenticationEntryPoint(entryPoint));

        return http.build();
    }
}
