package com.homehub.core.login.service;

import com.homehub.core.login.controller.LoginController;
import com.homehub.core.login.dto.SignupRequestDTO;
import com.homehub.core.login.entity.Users;
import com.homehub.core.login.mapper.SignupMapper;
import com.homehub.core.login.repo.LoginRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.logging.Logger;

@Service
public class LoginService {

    private final LoginRepo loginRepo;

    LoginService(final LoginRepo loginRepo) {
        this.loginRepo = loginRepo;
    }

    public Users register(SignupRequestDTO user) {
        Users mappedUser = SignupMapper.toDTO(user);
        return loginRepo.save(mappedUser);
    }

    public Users findByUserName(String username) {
        return loginRepo.findByUserName(username);
    }
}
