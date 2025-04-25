package com.example.shareCab.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.shareCab.model.User;
import com.example.shareCab.model.Driver;
import com.example.shareCab.repository.UserRepository;
import com.example.shareCab.repository.DriverRepository;

@Service
public class CombinedUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // First try to load user
        User user = userRepository.findByEmail(username);
        if (user != null) {
            return new CustomUserDetails(user);
        }

        // Then try to load driver
        Driver driver = driverRepository.findByEmail(username);
        if (driver != null) {
            return new CustomDriverDetails(driver);
        }

        // If neither found
        throw new UsernameNotFoundException("User or Driver not found!");
    }
}
