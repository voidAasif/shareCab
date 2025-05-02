package com.example.shareCab.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.example.shareCab.userdetails.CustomUserDetails;
import com.example.shareCab.model.User;
import com.example.shareCab.model.Driver;
import com.example.shareCab.repository.DriverRepository;
import com.example.shareCab.repository.UserRepository;
import com.example.shareCab.userdetails.CustomDriverDetails;

@Service
public class JwtUtils {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;

    private String SECRET_KEY = "CustomSecretKey";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        //add roles into token;
        if (userDetails instanceof @SuppressWarnings("unused") CustomUserDetails customUserDetails) {
            String email = userDetails.getUsername();
            User user = userRepository.findByEmail(email);
            claims.put("id", user.getId());
            claims.put("role", "USER");
        }
        if (userDetails instanceof @SuppressWarnings("unused") CustomDriverDetails customDriverDetails) {
            String email = userDetails.getUsername();
            Driver driver = driverRepository.findByEmail(email);
            claims.put("id", driver.getId());
            claims.put("role", "DRIVER");
        }

    return createToken(claims, userDetails.getUsername());
}

private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
}

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
