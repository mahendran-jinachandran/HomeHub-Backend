package com.homehub.core.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequestDTO {

    @NotBlank(message = "Username is required")
    @Size(max = 20, message = "Name cannot exceed 20 characters")
    private String userName;

    @NotBlank(message = "Password is required")
    @Size(max = 15, message = "Password cannot exceed 15 characters")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be a valid one")
    private String email;
}
