package com.example.shareCab.userdetails;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.shareCab.model.Driver;

public class CustomDriverDetails implements UserDetails{
    private Driver driver;

    public CustomDriverDetails(Driver driver){ //Initialize CustomDriverDetails with a User object from CustomDriverDetailsService;
        super();
        
        this.driver = driver;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // Retrieve the authorities (roles) assigned to the user in Spring Security;
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + driver.getRole());

        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() { // Retrieve the driver's password for authentication;
        return driver.getPassword();
    }

    @Override
    public String getUsername() { // Retrieve the driver's email, used as the username for authentication;
        return driver.getEmail();
    }

}
