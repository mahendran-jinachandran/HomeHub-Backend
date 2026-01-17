package com.homehub.core.login.mapper;


import com.homehub.core.login.dto.SignupRequestDTO;
import com.homehub.core.login.entity.UserStatus;
import com.homehub.core.login.entity.Users;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

public class SignupMapper {

    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static Users toDTO(SignupRequestDTO signupRequestDTO) {
        Users user = new Users();
        user.setUserName(signupRequestDTO.getUserName());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(signupRequestDTO.getPassword()));
        user.setRole(signupRequestDTO.getRole());
        user.setCreated_at(LocalDate.now());
        user.setUpdated_at(LocalDate.now());
        user.setStatus(UserStatus.ACTIVE);
        return user;
    }
}
