package com.example.shareCab.userdetails;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.shareCab.model.User;

public class CustomUserDetails implements UserDetails{
    private User user;

    public CustomUserDetails(User user){ //Initialize CustomUserDetails with a User object from CustomUserDetailsService;
        super();
        
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // Retrieve the authorities (roles) assigned to the user in Spring Security;
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + user.getRole());

        System.out.println("role: " + user.getRole()); //log;
        System.out.println("simple grant authority: " + simpleGrantedAuthority); //log;

        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() { // Retrieve the user's password for authentication;
        return user.getPassword();
    }

    @Override
    public String getUsername() { // Retrieve the user's email, used as the username for authentication;
        return user.getEmail();
    }

}
