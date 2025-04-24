package com.example.shareCab.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignupDTO {

    @NotBlank(message = "First name is required")
    @Size(max = 20, min = 3, message = "Please enter name between 3 to 20 characters!!")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = ".*[A-Za-z].*", message = "Password must contain at least one letter.")
    @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit.")
    @Pattern(regexp = ".*[^A-Za-z\\d].*", message = "Password must contain at least one special character.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;
}

